package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class Transfer {

    public Money amount;
    public CurrencyUnit currencyUnit;
    public String sourceAccountId;
    public String destinationAccountId;

    public Transfer() {}

    public Transfer(Money amount, CurrencyUnit currencyUnit, String sourceAccountId, String destinationAccountId) {
        this.amount = amount;
        this.currencyUnit = currencyUnit;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
    }

    @JsonProperty
    public Money getAmount() {
        return amount;
    }

    @JsonProperty
    public CurrencyUnit getCurrencyUnit() {
        return currencyUnit;
    }

    @JsonProperty
    public String getSourceAccountId() {
        return sourceAccountId;
    }

    @JsonProperty
    public String getDestinationAccountId() {
        return destinationAccountId;
    }
}
