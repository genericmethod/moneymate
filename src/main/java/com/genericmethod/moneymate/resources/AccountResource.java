package com.genericmethod.moneymate.resources;

import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.dao.AccountDao;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.MoneyAmount;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
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
    public MoneyAmount getBalance(@PathParam("id") @NotBlank String id) {
        return new MoneyAmount();
    }

    @POST
    @Timed
    public Account createAccount(@Valid Account account) {

        if(accountDao.getUserAccount(account.getUsername(), account.getCurrency()) != null){
            //cannot create an account for same username and currency combination
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

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
    public void deleteAccount(@PathParam("username") @NotBlank String username) {
        accountDao.deleteAccount(username);
    }

    @PUT
    @Timed
    @Path("/{username}/deposit")
    public Account deposit(@PathParam("username") String username, MoneyAmount moneyAmount) {

        final Account account = accountDao.getUserAccount(username, moneyAmount.getCurrency());

        if (account == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        double newBalance = new BigDecimal(account.getBalance()).add(new BigDecimal(moneyAmount.getAmount())).doubleValue();
        int accountId = accountDao.updateBalance(newBalance);

        return accountDao.getAccount(accountId);
    }

    @PUT
    @Timed
    @Path("/{id}/withdraw")
    public Account withdraw(@PathParam("id") String username, @Valid MoneyAmount moneyAmount) {

        final Account account = accountDao.getUserAccount(username, moneyAmount.getCurrency());

        if (account == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        if (moneyAmount.getAmount() > account.getBalance()){
            //money to withdraw must be a smaller amount than the account balance
            throw new WebApplicationException(Response.Status.EXPECTATION_FAILED);
        }

        double newBalance = new BigDecimal(account.getBalance()).subtract(new BigDecimal(moneyAmount.getAmount())).doubleValue();
        int accountId = accountDao.updateBalance(newBalance);

        return accountDao.getAccount(accountId);
    }

}
