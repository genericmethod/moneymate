package com.genericmethod.moneymate.dao;

import com.genericmethod.moneymate.model.Account;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface AccountDao {

    @SqlUpdate("CREATE TABLE account (id varchar(80) primary key," +
            " username varchar(25)," +
            " description varchar(100)," +
            " currency varchar(3)," +
            " balance decimal(20,2))")
    void createTable();

    @SqlUpdate("SELECT * FROM account where id = :id")
    Account getAccount(@Bind String id);

    @SqlUpdate("SELECT * FROM account")
    List<Account> getAllAccounts();

    @SqlUpdate("SELECT * FROM account WHERE username = :username")
    Account getUserAccount(@Bind String username);

    @SqlUpdate("INSERT INTO account (id, username, description, currency, balance)")
    Account createAccount(@BindBean Account account);

    @SqlUpdate("UPDATE account (id = :a.id," +
            " username = :a.username," +
            " description = :a.description," +
            " currency = :a.currency," +
            " balance = :a.balance)")
    Account updateAccount(@BindBean("a") Account account);

    @SqlUpdate("DELETE account where username = username")
    void deleteAccount(String username);

}
