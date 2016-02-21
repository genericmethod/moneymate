package com.genericmethod.moneymate.resources;

import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.MoneyAmount;
import com.genericmethod.moneymate.services.AccountDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private AccountDao accountService;

    public AccountResource(AccountDao accountService) {
        this.accountService = accountService;
    }

    @GET
    @Timed
    @Path("/{id}")
    public Account getAccount(@PathParam("id") String id) {
        return accountService.getAccount(id);
    }

    @GET
    @Timed
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GET
    @Timed
    @Path("/{id}/balance")
    public MoneyAmount getBalance(@PathParam("id") String id) {
        return accountService.getBalance(id);
    }

    @POST
    @Timed
    public Account createAccount(Account account) {
        return accountService.createAccount(account);
    }

    @PUT
    @Timed
    @Path("/{id}")
    public Account updateAccount(@PathParam("id") String id, Account account) {
        return accountService.updateAccount(account);
    }

    @DELETE
    @Timed
    @Path("/{id}")
    public void deleteAccount(@PathParam("id") String id) {
        accountService.deleteAccount(id);
    }

    @PUT
    @Timed
    @Path("/{id}/deposit")
    public Account deposit(@PathParam("id") String id, MoneyAmount moneyAmount) {
        return accountService.deposit(moneyAmount);
    }

    @PUT
    @Timed
    @Path("/{id}/withdraw")
    public Account withdraw(@PathParam("id") String id, MoneyAmount moneyAmount) {
        return accountService.withdraw(moneyAmount);
    }

}
