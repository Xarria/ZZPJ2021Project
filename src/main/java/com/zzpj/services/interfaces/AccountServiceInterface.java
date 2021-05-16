package com.zzpj.services.interfaces;

import com.zzpj.exceptions.EmailAlreadyExistsException;
import com.zzpj.exceptions.LoginAlreadyExistsException;
import com.zzpj.model.entities.Account;

import java.util.List;

public interface AccountServiceInterface {

    void createAccount(Account account) throws LoginAlreadyExistsException, EmailAlreadyExistsException;

    Account getAccountByLogin(String login);

    List<Account> getAllAccounts();

    void updateAccount(String login, Account updatedAccount);

    void activateAccount(String login);

    void deactivateAccount(String login);
}
