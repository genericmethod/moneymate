package com.genericmethod.moneymate.resources;

import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.User;
import com.genericmethod.moneymate.dao.AccountDao;
import com.genericmethod.moneymate.dao.UserDao;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserResourceTest {

    private static final UserDao userDao = mock(UserDao.class);
    private static final AccountDao accountDao = mock(AccountDao.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new UserResource(userDao, accountDao))
            .build();

    private final User user1 = new User(1, "vlad", "vlad@gmail.com");
    private final User user2 = new User(2, "nikolay", "nikolay@gmail.com");

    @Before
    public void setup() {
    }

    @After
    public void tearDown() {
        reset(userDao);
    }

    @Test
    public void testGetUser() {
        when(userDao.getUserByUsername(eq("vlad"))).thenReturn(user1);
        assertThat(resources.client().target("/v1/users/vlad").request().get(User.class))
                .isEqualTo(user1);
        verify(userDao).getUserByUsername("vlad");
    }

    @Test
    public void testGetAllUsers() {

        final List<User> users = Arrays.asList(user1, user2);
        when(userDao.getAllUsers()).thenReturn(users);

        List<User> userList = new ArrayList<>();
        assertThat(resources.client().target("/v1/users").request().get(userList.getClass()).size())
                .isEqualTo(2);
    }

    @Test
    public void testGetUserAccount(){

        Account account = new Account("1",
                "vlad",
                "Vlad's Account",
                new BigDecimal(123.00).setScale(2, BigDecimal.ROUND_UNNECESSARY),
                Currency.getInstance("EUR"));

        when(accountDao.getUserAccount("vlad")).thenReturn(account);

        assertThat(resources.client().target("/v1/users/vlad/account").request().get(Account.class))
                .isEqualTo(account);
    }

    @Test
    public void testCreateUser() {

        User newUser = new User("vlad","cfarrugia@gmail.com");
        User savedUser = new User(1,"vlad","vlad@gmail.com");

        when(userDao.createUser(newUser)).thenReturn(savedUser);
        assertThat(resources.client().target("/v1/users").request().post(Entity.json(newUser))
                .readEntity(User.class))
                .isEqualTo(savedUser);
        verify(userDao).createUser(newUser);
    }

    @Test
    public void testUpdateUser() {

        User userToUpdate = new User(1,"vlad", "vlad@gmail.com");
        when(userDao.updateUser(userToUpdate)).thenReturn(userToUpdate);

        assertThat(resources.client().target("/v1/users/1").request().put(Entity.json(userToUpdate))
                .readEntity(User.class))
                .isEqualTo(userToUpdate);

        verify(userDao).updateUser(userToUpdate);
    }

    @Test
    public void testDeleteUser() {

        doNothing().when(userDao).deleteUser("vlad");
        assertThat(resources.client().target("/v1/users/vlad").request().delete().getStatusInfo().getStatusCode())
                .isEqualTo(204);
        verify(userDao).deleteUser("vlad");
    }


}