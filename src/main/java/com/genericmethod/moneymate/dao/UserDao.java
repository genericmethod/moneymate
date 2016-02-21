package com.genericmethod.moneymate.dao;

import com.genericmethod.moneymate.dao.mapper.UserMapper;
import com.genericmethod.moneymate.model.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(UserMapper.class)
public interface UserDao {

    @SqlUpdate("CREATE TABLE user (id INT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
            " username varchar(25), " +
            "email varchar(25))")
    void createTable();

    @SqlQuery("SELECT * from user where id = :id")
    User getUserById(@Bind int id);

    @SqlQuery("SELECT * from user where username = :username")
    User getUserByUsername(@Bind String username);

    @SqlQuery("SELECT * from user")
    List<User> getAllUsers();

    @SqlUpdate("INSERT INTO user (username, email) values (:u.username, :u.email)")
    int createUser(@BindBean("u") User user);

    @SqlUpdate("UPDATE user SET id =:u.id, username =:u.username, email = :u.email")
    int updateUser(@BindBean("u") User user);

    @SqlUpdate("DELETE user where username")
    void deleteUser(@Bind String username);
}
