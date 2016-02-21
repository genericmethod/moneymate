package com.genericmethod.moneymate.resources;

import com.genericmethod.moneymate.model.Transfer;
import com.genericmethod.moneymate.services.TransferService;
import io.dropwizard.jersey.validation.ValidationErrorMessage;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TransferResourceTest {

    private static final TransferService transferService = mock(TransferService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TransferResource(transferService))
            .build();

    @Before
    public void setup() {
    }

    @After
    public void tearDown() {
        reset(transferService);
    }

    @Test
    public void testTransfer() {

        Transfer transfer = new Transfer(new BigDecimal(123).setScale(2, BigDecimal.ROUND_UNNECESSARY), Currency.getInstance("EUR"), "1", "2");

        doNothing().when(transferService).transfer(transfer);
        assertThat(resources.client().target("/v1/transfers").request().post(Entity.json(transfer)).getStatusInfo()).isEqualTo(Response.Status.NO_CONTENT);

        verify(transferService).transfer(transfer);
    }

    @Test
    public void testTransferMissingSourceAccount() {
        Transfer transfer = new Transfer(new BigDecimal(123).setScale(2, BigDecimal.ROUND_UNNECESSARY), Currency.getInstance("EUR"), null, "2");
        final Response post = resources.client().target("/v1/transfers").request().post(Entity.json(transfer));
        assertThat(post.getStatus()).isEqualTo(422);
        ValidationErrorMessage msg = post.readEntity(ValidationErrorMessage.class);
        assertThat(msg.getErrors()).containsOnly("sourceAccountId may not be null");
    }

    @Test
    public void testTransferMissingDestinationAccount() {
        Transfer transfer = new Transfer(new BigDecimal(123).setScale(2, BigDecimal.ROUND_UNNECESSARY), Currency.getInstance("EUR"), "1", null);
        final Response post = resources.client().target("/v1/transfers").request().post(Entity.json(transfer));
        assertThat(post.getStatus()).isEqualTo(422);
        ValidationErrorMessage msg = post.readEntity(ValidationErrorMessage.class);
        assertThat(msg.getErrors()).containsOnly("destinationAccountId may not be null");
    }

    @Test
    public void testTransferMissingAmount() {
        Transfer transfer = new Transfer(null, Currency.getInstance("EUR"), "1", "2");
        final Response post = resources.client().target("/v1/transfers").request().post(Entity.json(transfer));
        assertThat(post.getStatus()).isEqualTo(422);
        ValidationErrorMessage msg = post.readEntity(ValidationErrorMessage.class);
        assertThat(msg.getErrors()).containsOnly("amount may not be null");
    }

    @Test
    public void testTransferMissingCurrency() {
        Transfer transfer = new Transfer(new BigDecimal(123).setScale(2, BigDecimal.ROUND_UNNECESSARY), null, "1", "2");
        final Response post = resources.client().target("/v1/transfers").request().post(Entity.json(transfer));
        assertThat(post.getStatus()).isEqualTo(422);
        ValidationErrorMessage msg = post.readEntity(ValidationErrorMessage.class);
        assertThat(msg.getErrors()).containsOnly("currency may not be null");
    }

}