package com.genericmethod.moneymate.resources;

import com.genericmethod.moneymate.model.Transfer;
import com.genericmethod.moneymate.services.TransferService;
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

        Transfer transfer = new Transfer(new BigDecimal(123).setScale(2,BigDecimal.ROUND_UNNECESSARY), Currency.getInstance("EUR"),"1","2");

        doNothing().when(transferService).transfer(transfer);
        assertThat(resources.client().target("/v1/transfers").request().post(Entity.json(transfer)).getStatusInfo()).isEqualTo(Response.Status.OK);

        verify(transferService).transfer(transfer);
    }

}