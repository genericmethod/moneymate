package com.genericmethod.moneymate.resources;

import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.MoneyAmount;
import com.genericmethod.moneymate.dao.AccountDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private AccountDao accountDao;

    public AccountResource(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GET
    @Timed
    @Path("/{id}")
    public Account getAccount(@PathParam("id") int id) {
        return accountDao.getAccount(id);
    }

    @GET
    @Timed
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @GET
    @Timed
    @Path("/{id}/balance")
    public MoneyAmount getBalance(@PathParam("id") String id) {
        return new MoneyAmount();
    }

    @POST
    @Timed
    public Account createAccount(Account account) {
        final int accountId = accountDao.createAccount(account);
        return accountDao.getAccount(accountId);
    }

    @PUT
    @Timed
    @Path("/{id}")
    public Account updateAccount(@PathParam("id") Integer id, Account account) {
        final int accountId = accountDao.updateAccount(account);
        return accountDao.getAccount(accountId);
    }

    @DELETE
    @Timed
    @Path("/{username}")
    public void deleteAccount(@PathParam("username") String username) {
        accountDao.deleteAccount(username);
    }

    @PUT
    @Timed
    @Path("/{id}/deposit")
    public Account deposit(@PathParam("id") String id, MoneyAmount moneyAmount) {
          return new Account();
    }

    @PUT
    @Timed
    @Path("/{id}/withdraw")
    public Account withdraw(@PathParam("id") String id, MoneyAmount moneyAmount) {
        return new Account();
    }

}
