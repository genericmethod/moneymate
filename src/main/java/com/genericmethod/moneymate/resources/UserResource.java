package com.genericmethod.moneymate.resources;

import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Currency;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    public UserResource() {
    }

    @GET
    @Timed
    public User getAll() {
        return new User("chris", "cfarrugia@gmail.com");
    }

    @GET
    @Timed
    @Path("/{id}")
    public User get(@PathParam("id") String id) {
        return new User("chris", "cfarrugia@gmail.com");
    }

    @GET
    @Timed
    @Path("{id}/account")
    public Account account(@PathParam("id") String id){
        return new Account(new User("chris", "cfarrugia@gmail.com"),"desc", new BigDecimal(123), Currency.getInstance("EUR"));
    }

    @POST
    @Timed
    public User create(User user) {
        return new User("chris", "cfarrugia@gmail.com");
    }

    @PUT
    @Timed
    @Path("/{id}")
    public User update(User user) {
        return new User("chris", "cfarrugia@gmail.com");
    }

}