package com.genericmethod.moneymate.model;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.UUID;

final public class Account {

    final private String id;
    final private User user;
    final private String description;
    final private Money balance;
    final private CurrencyUnit currency;

    public Account(User user, String description, Money balance, CurrencyUnit currency) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.description = description;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(String id, User user, String description, Money balance, CurrencyUnit currency) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.balance = balance;
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public Money getBalance() {
        return balance;
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }
}
