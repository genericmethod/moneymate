package com.genericmethod.moneymate.model;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

final public class Transfer {

    final public Money amount;
    final public CurrencyUnit currencyUnit;
    final public String sourceAccountId;
    final public String destinationAccountId;

    public Transfer(Money amount, CurrencyUnit currencyUnit, String sourceAccountId, String destinationAccountId) {
        this.amount = amount;
        this.currencyUnit = currencyUnit;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
    }

    public Money getAmount() {
        return amount;
    }

    public CurrencyUnit getCurrencyUnit() {
        return currencyUnit;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }
}
