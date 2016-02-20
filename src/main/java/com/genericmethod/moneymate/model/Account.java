package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public class Account {

    private String id;

    @NotEmpty
    private User user;

    @NotEmpty
    private String description;

    @NotEmpty
    private BigDecimal balance;

    @NotEmpty
    private Currency currency;

    public Account() {}

    public Account(User user, String description, BigDecimal balance, Currency currency) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.description = description;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(String id, User user, String description, BigDecimal balance, Currency currency) {
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
    public BigDecimal getBalance() {
        return balance;
    }

    @JsonProperty
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account that = (Account) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.user, that.user) &&
                Objects.equal(this.description, that.description) &&
                Objects.equal(this.balance, that.balance) &&
                Objects.equal(this.currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, user, description, balance, currency);
    }
}
