package com.genericmethod.moneymate.model;

import java.util.UUID;

final public class User {

    final private String id;
    final private String username;
    final private String email;

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





}
