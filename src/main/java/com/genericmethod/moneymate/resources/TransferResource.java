package com.genericmethod.moneymate.resources;


import com.codahale.metrics.annotation.Timed;
import com.genericmethod.moneymate.dao.AccountDao;
import com.genericmethod.moneymate.dao.UserDao;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.Transfer;
import org.skife.jdbi.v2.sqlobject.Transaction;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Path("/v1/transfers")
@Produces(MediaType.APPLICATION_JSON)
public class TransferResource {

    private UserDao userDao;
    private AccountDao accountDao;

    public TransferResource(UserDao userDao, AccountDao accountDao) {

        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @POST
    @Timed
    @Transaction
    public void transfer(@Valid Transfer transfer) {

        final Account sourceAccount = accountDao.getAccountForUpdate(transfer.getSourceAccountId());
        final Account destinationAccount = accountDao.getAccountForUpdate(transfer.getDestinationAccountId());

        if (sourceAccount == null) {
            throw new WebApplicationException("source account not found", Response.Status.EXPECTATION_FAILED);
        }

        if (destinationAccount == null) {
            throw new WebApplicationException("destination account not found", Response.Status.EXPECTATION_FAILED);
        }

        //TODO currencies must match too

        if (transfer.getAmount() > sourceAccount.getBalance()){
            throw new WebApplicationException("not enough funds available for transfer", Response.Status.EXPECTATION_FAILED);
        }

        final BigDecimal newSourceAmount = new BigDecimal(sourceAccount.getBalance()).setScale(2, BigDecimal.ROUND_UNNECESSARY)
                .subtract(new BigDecimal(transfer.getAmount()).setScale(2, BigDecimal.ROUND_UNNECESSARY));

        final BigDecimal newDestinationAmount = new BigDecimal(destinationAccount.getBalance()).setScale(2, BigDecimal.ROUND_UNNECESSARY)
                .add(new BigDecimal(transfer.getAmount()).setScale(2, BigDecimal.ROUND_UNNECESSARY));


        accountDao.updateBalance(transfer.getSourceAccountId(), newSourceAmount.doubleValue());
        accountDao.updateBalance(transfer.getDestinationAccountId(), newDestinationAmount.doubleValue());

    }
}

