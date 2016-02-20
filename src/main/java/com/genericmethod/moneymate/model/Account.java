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
    private String username;

    @NotEmpty
    private String description;

    @NotEmpty
    private BigDecimal balance;

    @NotEmpty
    private Currency currency;

    public Account() {}

    public Account(String username, String description, BigDecimal balance, Currency currency) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.description = description;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(String id, String username, String description, BigDecimal balance, Currency currency) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.balance = balance;
        this.currency = currency;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getUsername() {
        return username;
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
                Objects.equal(this.username, that.username) &&
                Objects.equal(this.description, that.description) &&
                Objects.equal(this.balance, that.balance) &&
                Objects.equal(this.currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, username, description, balance, currency);
    }
}
