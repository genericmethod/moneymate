package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;

public class Transfer {

    @NotNull
    public Double amount;

    @NotNull
    public String currency;

    @NotNull
    public String sourceAccountId;

    @NotNull
    public String destinationAccountId;

    public Transfer() {}

    public Transfer(Double amount, String currency, String sourceAccountId, String destinationAccountId) {
        this.amount = amount;
        this.currency = currency;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
    }

    @JsonProperty
    public Double getAmount() {
        return amount;
    }

    @JsonProperty
    public String getCurrency() {
        return currency;
    }

    @JsonProperty
    public String getSourceAccountId() {
        return sourceAccountId;
    }

    @JsonProperty
    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer that = (Transfer) o;

        return Objects.equal(this.amount, that.amount) &&
                Objects.equal(this.currency, that.currency) &&
                Objects.equal(this.sourceAccountId, that.sourceAccountId) &&
                Objects.equal(this.destinationAccountId, that.destinationAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount, currency, sourceAccountId, destinationAccountId);
    }
}
