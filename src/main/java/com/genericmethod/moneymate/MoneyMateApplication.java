package com.genericmethod.moneymate;

import com.genericmethod.moneymate.config.MoneyMateConfiguration;
import com.genericmethod.moneymate.resources.AccountResource;
import com.genericmethod.moneymate.resources.UserResource;
import com.genericmethod.moneymate.services.UserService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;


public class MoneyMateApplication extends Application<MoneyMateConfiguration> {

    public static void main(String[] args) throws Exception {
        new MoneyMateApplication().run(args);
    }


    @Override
    public void run(MoneyMateConfiguration moneyMateConfiguration, Environment environment) throws Exception {

        UserResource userResource = new UserResource(new UserService());
        AccountResource accountResource = new AccountResource();
        //TransferResource transferResource = new TransferResource();

        environment.jersey().register(userResource);
        environment.jersey().register(accountResource);
        //environment.jersey().register(transferResource);
    }
}