package com.genericmethod.moneymate;

import com.genericmethod.moneymate.config.MoneyMateConfiguration;
import com.genericmethod.moneymate.resources.AccountResource;
import com.genericmethod.moneymate.resources.TransferResource;
import com.genericmethod.moneymate.resources.UserResource;
import com.genericmethod.moneymate.services.AccountDao;
import com.genericmethod.moneymate.services.UserDao;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;


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


        UserResource userResource = new UserResource(userDao);
        AccountResource accountResource = new AccountResource(accountDao);
        TransferResource transferResource = new TransferResource();

        environment.jersey().register(userResource);
        environment.jersey().register(accountResource);
        environment.jersey().register(transferResource);
    }
}