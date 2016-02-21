package com.genericmethod.moneymate.dao;

import com.genericmethod.moneymate.model.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

public interface UserDao {

    @SqlUpdate("CREATE TABLE user (id auto_increment primary key," +
            " username varchar(25), " +
            "email varchar(25))")
    void createTable();

    @SqlUpdate("SELECT * from user where id = :id")
    User getUserById(@Bind Integer id);

    @SqlUpdate("SELECT * from user where username = :username")
    User getUserByUsername(@Bind String username);

    @SqlUpdate("SELECT * from user")
    List<User> getAllUsers();

    @SqlUpdate("INSERT INTO user (id, username, email) values (:id, :username, :email)")
    User createUser(@BindBean User user);

    @SqlUpdate("UPDATE user SET id =:u.id, username =:u.username, email = :u.email")
    User updateUser(@BindBean("u") User user);

    @SqlUpdate("DELETE user where username")
    void deleteUser(@Bind String username);
}
