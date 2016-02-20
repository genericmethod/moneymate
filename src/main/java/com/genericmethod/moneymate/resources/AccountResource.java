package com.genericmethod.moneymate.resources;

import com.genericmethod.moneymate.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/accounts")
public class AccountResource {

    @GET
    public User getAccount(@QueryParam("id") String id) {
        return null;
    }
}
