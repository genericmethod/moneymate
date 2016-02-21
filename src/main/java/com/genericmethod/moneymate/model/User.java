package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;

public class User {

    private int id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
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
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.username, that.username) &&
                Objects.equal(this.email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, username, email);
    }
}
