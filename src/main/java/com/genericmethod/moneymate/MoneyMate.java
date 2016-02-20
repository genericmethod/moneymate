package com.genericmethod.moneymate;


import static spark.Spark.*;

public class MoneyMate {
    public static void main( String[] args ) {

        get("/hello", (req, res) -> "Hello World");

        /**
         * Users
         */

        /**
         * Get user by id
         */
        get("/v1/users/:id", (req, res) ->{
            return null;
        });

        /**
         * Get user account
         */
        get("/v1//users/:id/account", (req, res) ->{
            return null;
        });

        /**
         * Get all users
         */
        get("/v1//users", (req, res) ->{
            return null;
        });

        /**
         * Add a new user
         */
        post("/v1//users", (request, response) ->{
           return null;
        });

        /**
         * Update a user
         */
        put("/v1//users/:id", (request, response) -> {
            return null;
        });

        /**
         * Delete a user
         */
        delete("/v1//users/:id", (request, response) -> {
            return null;
        });

        /**
         * Account
         */

        /**
         * Get an account by id
         */
        get("/v1//accounts/:id", (req, res) ->{
            return null;
        });

        /**
         * Get all accounts
         */
        get("/v1/accounts", (req, res) ->{
            return null;
        });

        /**
         * Get account balance
         */
        get("/v1/accounts/:id/balance", (req, res) ->{
            return null;
        });

        /**
         * Add a new account
         */
        post("/v1/accounts", (request, response) ->{
            return null;
        });

        /**
         * Update an account
         */
        put("/v1/accounts/:id", (request, response) -> {
            return null;
        });

        /**
         * Delete an account
         */
        delete("/v1/accounts/:id", (request, response) -> {
            return null;
        });

        /**
         * Deposit money into an account
         */
        post("/v1/account/:id/deposit", (request, response) -> {
           return null;
        });

        /**
         * Withdraw money from an account
         */
        post("/v1/account/:id/withdraw", (request, response) -> {
            return null;
        });

        post("/v1/transfers", (request, response) -> {
            return null;
        });

    }


}
