package com.genericmethod.moneymate;

import com.genericmethod.moneymate.config.MoneyMateConfiguration;
import com.genericmethod.moneymate.dao.AccountDao;
import com.genericmethod.moneymate.dao.UserDao;
import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.User;
import com.genericmethod.moneymate.resources.AccountResource;
import com.genericmethod.moneymate.resources.TransferResource;
import com.genericmethod.moneymate.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;


public class MoneyMateApplication extends Application<MoneyMateConfiguration> {

    public static void main(String[] args) throws Exception {
        new MoneyMateApplication().run(args);
    }


    @Override
    public void run(MoneyMateConfiguration moneyMateConfiguration, Environment environment) throws Exception {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, moneyMateConfiguration.getDataSourceFactory(), "h2");
        final UserDao userDao = jdbi.onDemand(UserDao.class);
        final AccountDao accountDao = jdbi.onDemand(AccountDao.class);
        userDao.createTable();
        accountDao.createTable();

        userDao.createUser(new User("chris","cfarrugia@gmail.com"));
        userDao.createUser(new User("mark","mark@gmail.com"));

        accountDao.createAccount(new Account("chris","salary account",
                new BigDecimal(888.00).setScale(2, RoundingMode.UNNECESSARY),
                Currency.getInstance("EUR").getCurrencyCode()));

        accountDao.createAccount(new Account("mark","salary account",
                new BigDecimal(999.00).setScale(2, RoundingMode.UNNECESSARY),
                Currency.getInstance("EUR").getCurrencyCode()));

        UserResource userResource = new UserResource(userDao, accountDao);
        AccountResource accountResource = new AccountResource(accountDao);
        TransferResource transferResource = new TransferResource();

        environment.jersey().register(userResource);
        environment.jersey().register(accountResource);
        environment.jersey().register(transferResource);
    }
}