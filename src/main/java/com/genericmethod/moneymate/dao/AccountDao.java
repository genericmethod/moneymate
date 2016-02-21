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

    @SqlQuery("SELECT * FROM account WHERE id = :id")
    Account getAccount(@Bind("id") int id);

    @SqlQuery("SELECT * FROM account")
    List<Account> getAllAccounts();

    @SqlQuery("SELECT * FROM account WHERE username = :username")
    Account getUserAccount(@Bind("username") String username);

    @SqlQuery("SELECT * FROM account WHERE username = :username AND currenct = :currency")
    Account getUserAccount(@Bind("username") String username, @Bind("currency") String currency);

    @SqlUpdate("INSERT INTO account (username, description, currency, balance) " +
            "values (:a.username, :a.description, :a.currency, :a.balance)")
    int createAccount(@BindBean("a") Account account);

    @SqlUpdate("UPDATE account username = :a.username," +
            " description = :a.description," +
            " currency = :a.currency," +
            " balance = :a.balance WHERE id = :a.id")
    int updateAccount(@BindBean("a") Account account);

    @SqlUpdate("UPDATE balance = :a.balance WHERE id = :a.id")
    int updateBalance(@BindBean("a") double moneyAmount);

    @SqlUpdate("DELETE FROM account WHERE username = username")
    void deleteAccount(@Bind("username") String username);

}
