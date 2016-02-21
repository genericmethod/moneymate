package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class MoneyAmount {

    @NotNull
    @DecimalMin("0.01")
    private Double amount;

    @NotEmpty
    private String currency;

    public MoneyAmount(){}

    public MoneyAmount(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @JsonProperty
    public Double getAmount() {
        return amount;
    }

    @JsonProperty
    public String getCurrency() {
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
