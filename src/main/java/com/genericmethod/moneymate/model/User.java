package com.genericmethod.moneymate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.UUID;

public class User {

    private String id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    public User() {}

    public User(String username, String email) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
    }

    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
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
    public String getEmail() {
        return email;
    }
}
