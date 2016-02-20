package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.UUID;

public class Account {

    private String id;

    @NotEmpty
    private User user;

    @NotEmpty
    private String description;

    @NotEmpty
    private Money balance;

    @NotEmpty
    private CurrencyUnit currency;

    public Account() {}

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

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public User getUser() {
        return user;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public Money getBalance() {
        return balance;
    }

    @JsonProperty
    public CurrencyUnit getCurrency() {
        return currency;
    }
}
