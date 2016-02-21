package com.genericmethod.moneymate.resources;

import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.User;
import com.genericmethod.moneymate.dao.AccountDao;
import com.genericmethod.moneymate.dao.UserDao;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserDao userDao;
    private AccountDao accountDao;

    public UserResource(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @GET
    @Timed
    public List<User> getAll() {
        return userDao.getAllUsers();
    }

    @GET
    @Timed
    @Path("/{username}")
    public User get(@PathParam("username") String username) {
        return userDao.getUserByUsername(username);
    }

    @GET
    @Timed
    @Path("/{username}/account")
    public Account account(@PathParam("username") String username){
        return accountDao.getUserAccount(username);
    }

    @POST
    @Timed
    public User create(@Valid User user) {
        return userDao.createUser(user);
    }

    @PUT
    @Timed
    @Path("/{id}")
    public User update(@PathParam("id") String id, @Valid User user) {
        return userDao.updateUser(user);
    }

    @DELETE
    @Timed
    @Path("/{username}")
    public void delete(@PathParam("username") String username) {
        userDao.deleteUser(username);
    }



}