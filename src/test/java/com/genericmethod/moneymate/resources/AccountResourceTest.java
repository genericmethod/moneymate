package com.genericmethod.moneymate.resources;

import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.MoneyAmount;
import com.genericmethod.moneymate.services.AccountDao;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AccountResourceTest {

    private static final AccountDao accountDao = mock(AccountDao.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new AccountResource(accountDao))
            .build();

    private final Account account1 = new Account("1", "vlad", "description", new BigDecimal(123.00).setScale(2, RoundingMode.UNNECESSARY), Currency.getInstance("EUR"));
    private final Account account2 = new Account("2", "nik", "description", new BigDecimal(123.00).setScale(2, RoundingMode.UNNECESSARY), Currency.getInstance("EUR"));

    @Before
    public void setup() {
    }

    @After
    public void tearDown() {
        reset(accountDao);
    }

    @Test
    public void testGetAccount() {
        when(accountDao.getAccount(eq("1"))).thenReturn(account1);
        assertThat(resources.client().target("/v1/accounts/1").request().get(Account.class))
                .isEqualTo(account1);
        verify(accountDao).getAccount("1");
    }

    @Test
    public void testGetAllAccounts() {

        final List<Account> accounts = Arrays.asList(account1, account2);
        when(accountDao.getAllAccounts()).thenReturn(accounts);

        List<Account> accountList = new ArrayList<>();
        assertThat(resources.client().target("/v1/accounts").request().get(accountList.getClass()).size())
                .isEqualTo(2);
    }

    @Test
    public void testGetAccountBalance(){

        MoneyAmount moneyAmount = new MoneyAmount(new BigDecimal(123.00).setScale(2, BigDecimal.ROUND_UNNECESSARY),
                Currency.getInstance("EUR"));

        when(accountDao.getBalance("1")).thenReturn(moneyAmount);

        assertThat(resources.client().target("/v1/accounts/1/balance").request().get(MoneyAmount.class))
                .isEqualTo(moneyAmount);

        verify(accountDao).getBalance("1");
    }

    @Test
    public void testCreateAccount() {

        Account newAccount = new Account("vlad",
                "description",
                new BigDecimal(123.00).setScale(2, RoundingMode.UNNECESSARY), Currency.getInstance("EUR"));

        Account savedAccount = new Account("1",
                "vlad",
                "description",
                new BigDecimal(123.00).setScale(2, RoundingMode.UNNECESSARY), Currency.getInstance("EUR"));

        when(accountDao.createAccount(newAccount)).thenReturn(savedAccount);
        assertThat(resources.client().target("/v1/accounts").request().post(Entity.json(newAccount))
                .readEntity(Account.class))
                .isEqualTo(savedAccount);

        verify(accountDao).createAccount(newAccount);
    }

    @Test
    public void testUpdateAccount() {

        Account updatedAccount = new Account("vlad",
                "description",
                new BigDecimal(123.00).setScale(2, RoundingMode.UNNECESSARY), Currency.getInstance("EUR"));

        when(accountDao.updateAccount(updatedAccount)).thenReturn(updatedAccount);

        assertThat(resources.client().target("/v1/accounts/1").request().put(Entity.json(updatedAccount))
                .readEntity(Account.class))
                .isEqualTo(updatedAccount);

        verify(accountDao).updateAccount(updatedAccount);
    }

    @Test
    public void testDeleteAccount() {

        doNothing().when(accountDao).deleteAccount("1");
        assertThat(resources.client().target("/v1/accounts/1").request().delete().getStatusInfo().getStatusCode())
                .isEqualTo(204);
        verify(accountDao).deleteAccount("1");
    }

    @Test
         public void testAccountDeposit() {

        final MoneyAmount moneyAmount = new MoneyAmount(new BigDecimal(123.00).setScale(2, BigDecimal.ROUND_UNNECESSARY),
                Currency.getInstance("EUR"));

        when(accountDao.deposit(moneyAmount)).thenReturn(account1);

        assertThat(resources.client().target("/v1/accounts/1/deposit").request().put(Entity.json(moneyAmount))
                .readEntity(Account.class))
                .isEqualTo(account1);

        verify(accountDao).deposit(moneyAmount);
    }

    @Test
    public void testAccountWithdrawal() {

        final MoneyAmount moneyAmount = new MoneyAmount(new BigDecimal(123.00).setScale(2, BigDecimal.ROUND_UNNECESSARY),
                Currency.getInstance("EUR"));

        when(accountDao.withdraw(moneyAmount)).thenReturn(account2);

        assertThat(resources.client().target("/v1/accounts/1/withdraw").request().put(Entity.json(moneyAmount))
                .readEntity(Account.class))
                .isEqualTo(account2);

        verify(accountDao).withdraw(moneyAmount);
    }


}