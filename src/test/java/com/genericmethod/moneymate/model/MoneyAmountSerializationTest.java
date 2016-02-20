package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class MoneyAmountSerializationTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final MoneyAmount moneyAmount = new MoneyAmount(new BigDecimal(123.00).setScale(2, BigDecimal.ROUND_UNNECESSARY),
                Currency.getInstance("EUR"));

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/moneyamount.json"), MoneyAmount.class));

        assertThat(MAPPER.writeValueAsString(moneyAmount)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final MoneyAmount moneyAmount = new MoneyAmount(new BigDecimal(123.00).setScale(2, BigDecimal.ROUND_UNNECESSARY),
                Currency.getInstance("EUR"));

        assertThat(MAPPER.readValue(fixture("fixtures/moneyamount.json"), MoneyAmount.class))
                .isEqualTo(moneyAmount);
    }
}
