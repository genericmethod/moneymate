package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Currency;

public class MoneyAmount {

    @NotEmpty
    private BigDecimal amount;

    @NotEmpty
    private Currency currency;

    public MoneyAmount(){}

    public MoneyAmount(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @JsonProperty
    public BigDecimal getAmount() {
        return amount;
    }

    @JsonProperty
    public Currency getCurrency() {
        return currency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoneyAmount that = (MoneyAmount) o;

        return Objects.equal(this.amount, that.amount) &&
                Objects.equal(this.currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount, currency);
    }
}
