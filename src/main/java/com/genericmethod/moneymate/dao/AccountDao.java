package com.genericmethod.moneymate.dao;

import com.genericmethod.moneymate.dao.mapper.AccountMapper;
import com.genericmethod.moneymate.model.Account;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(AccountMapper.class)
public interface AccountDao {

    @SqlUpdate("CREATE TABLE account (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            " username varchar(25)," +
            " description varchar(100)," +
            " currency varchar(300)," +
            " balance decimal(20,2))")
    void createTable();

    @SqlQuery("SELECT * FROM account where id = :id")
    Account getAccount(@Bind int id);

    @SqlQuery("SELECT * FROM account")
    List<Account> getAllAccounts();

    @SqlQuery("SELECT * FROM account WHERE username = :username")
    Account getUserAccount(@Bind String username);

    @SqlUpdate("INSERT INTO account (username, description, currency, balance) " +
            "values (:a.username, :a.description, :a.currency, :a.balance)")
    int createAccount(@BindBean("a") Account account);

    @SqlUpdate("UPDATE account (id = :a.id," +
            " username = :a.username," +
            " description = :a.description," +
            " currency = :a.currency," +
            " balance = :a.balance)")
    int updateAccount(@BindBean("a") Account account);

    @SqlUpdate("DELETE account where username = username")
    void deleteAccount(String username);

}
