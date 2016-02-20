package com.genericmethod.moneymate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.User;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountSerializationTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final User user = new User("1", "genericmethod", "cfarrugia@gmail.com");
        final Account account = new Account("1", user, "description",
                new BigDecimal(123.00).setScale(2, BigDecimal.ROUND_UNNECESSARY),
                Currency.getInstance("EUR"));


        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/account.json"), Account.class));

        assertThat(MAPPER.writeValueAsString(account)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final User user = new User("1", "genericmethod", "cfarrugia@gmail.com");
        final Account account = new Account("1", user, "description",
                new BigDecimal(123.00).setScale(2, BigDecimal.ROUND_UNNECESSARY),
                Currency.getInstance("EUR"));

        assertThat(MAPPER.readValue(fixture("fixtures/account.json"), Account.class))
                .isEqualTo(account);
    }
}
