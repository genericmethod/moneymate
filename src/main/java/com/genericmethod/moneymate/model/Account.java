package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class Account {

    private int id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String description;

    @NotNull
    @DecimalMin("0.00")
    private Double balance;

    @NotEmpty
    private String currency;

    public Account() {}

    public Account(String username, String description, Double balance, String currency) {
        this.username = username;
        this.description = description;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(int id, String username, String description, Double balance, String currency) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.balance = balance;
        this.currency = currency;
    }

    @JsonProperty
    public int getId() {
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
    public Double getBalance() {
        return balance;
    }

    @JsonProperty
    public String getCurrency() {
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
