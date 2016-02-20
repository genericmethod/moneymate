package com.genericmethod.moneymate.resources;

import com.genericmethod.moneymate.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    public UserResource() {
    }

    @GET
    public User getUser(@QueryParam("id") String id) {
        return null;
    }
}