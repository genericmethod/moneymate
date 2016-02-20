package com.genericmethod.moneymate.resources;

import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.User;
import com.genericmethod.moneymate.services.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Timed
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GET
    @Timed
    @Path("/{username}")
    public User get(@PathParam("username") String username) {
        return userService.getUserById(username);
    }

    @GET
    @Timed
    @Path("/{username}/account")
    public Account account(@PathParam("username") String username){
        return userService.getUserAccount(username);
    }

    @POST
    @Timed
    public User create(User user) {
        return userService.createUser(user);
    }

    @PUT
    @Timed
    @Path("/{id}")
    public User update(@PathParam("id") String id, User user) {
        return userService.updateUser(user);
    }

    @DELETE
    @Timed
    @Path("/{username}")
    public void delete(@PathParam("username") String username) {
        userService.deleteUser(username);
    }



}