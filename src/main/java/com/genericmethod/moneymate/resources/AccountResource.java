package com.genericmethod.moneymate.resources;

import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.dao.AccountDao;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.MoneyAmount;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public MoneyAmount getBalance(@PathParam("id") @NotNull Integer id) {
        final Account account = accountDao.getAccount(id);

        if(account == null){
            throw new WebApplicationException("account not found", Response.Status.NOT_FOUND);
        }

        return new MoneyAmount(account.getBalance(),account.getCurrency());
    }

    @POST
    @Timed
    public Account createAccount(@Valid Account account) {

        if(accountDao.getUserAccount(account.getUsername(), account.getCurrency()) != null){
            //cannot create an account for same username and currency combination
            throw new WebApplicationException("account with same currency already exists for user",
                    Response.Status.BAD_REQUEST);
        }

        final int accountId = accountDao.createAccount(account);
        return accountDao.getAccount(accountId);
    }

    @PUT
    @Timed
    @Path("/{id}")
    public Account updateAccount(@PathParam("id") @NotNull Integer id, @Valid Account account) {

        if(id != account.getId()){
            throw new WebApplicationException("id mismatch", Response.Status.BAD_REQUEST);
        }

        final Account acc = accountDao.getAccount(id);
        if (acc == null) {
            throw new WebApplicationException("account not found", Response.Status.NOT_FOUND);
        }

        accountDao.updateAccount(account);
        return accountDao.getAccount(id);
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
    public Account deposit(@PathParam("username") String username, @Valid MoneyAmount moneyAmount) {

        final Account account = accountDao.getUserAccountForUpdate(username, moneyAmount.getCurrency());

        if (account == null){
            throw new WebApplicationException("account not found", Response.Status.NOT_FOUND);
        }

        double newBalance = new BigDecimal(account.getBalance()).add(new BigDecimal(moneyAmount.getAmount())).doubleValue();
        accountDao.updateBalance(account.getId(), newBalance);

        return accountDao.getAccount(account.getId());
    }

    @PUT
    @Timed
    @Path("/{username}/withdraw")
    public Account withdraw(@PathParam("username") String username, @Valid MoneyAmount moneyAmount) {

        final Account account = accountDao.getUserAccountForUpdate(username, moneyAmount.getCurrency());

        if (account == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        if (moneyAmount.getAmount() > account.getBalance()){
            //money to withdraw must be a smaller amount than the account balance
            throw new WebApplicationException("withdrawal amount cannot be greater than balance",
                    Response.Status.EXPECTATION_FAILED);
        }

        double newBalance = new BigDecimal(account.getBalance()).subtract(new BigDecimal(moneyAmount.getAmount())).doubleValue();
        int accountId = accountDao.updateBalance(account.getId(), newBalance);

        return accountDao.getAccount(accountId);
    }

}
