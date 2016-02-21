package com.genericmethod.moneymate.services;

import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.User;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface UserDao {

    @SqlUpdate("CREATE TABLE user (id varchar(80) auto_increment primary key, username varchar(25), email varchar(25))")
    void createTable();

    User getUserById(String s);

    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(String username);

    Account getUserAccount(String username);
}
