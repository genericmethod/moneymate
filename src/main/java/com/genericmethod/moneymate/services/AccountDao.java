package com.genericmethod.moneymate.services;

import com.genericmethod.moneymate.model.Account;
import com.genericmethod.moneymate.model.MoneyAmount;

import java.util.List;

public interface AccountDao {

    void createTable();

    Account getAccount(String id);

    List<Account> getAllAccounts();

    MoneyAmount getBalance(String id);

    Account createAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(String id);

    Account deposit(MoneyAmount moneyAmount);

    Account withdraw(MoneyAmount moneyAmount);
}
