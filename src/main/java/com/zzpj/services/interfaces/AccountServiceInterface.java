package com.zzpj.services.interfaces;

import com.zzpj.DTOs.AccountDTO;
import com.zzpj.exceptions.EmailAlreadyExistsException;
import com.zzpj.exceptions.LoginAlreadyExistsException;
import com.zzpj.model.AccessLevel;
import com.zzpj.model.Account;

import java.util.List;

public interface AccountServiceInterface {

    void createAccount(Account account);

    AccountDTO getAccountByLogin(String login);

    AccountDTO getAllAccounts();

    void updateAccount(String login, Account updatedAccount);

    void activateAccount(String login);

    void deactivateAccount(String login);

    void addAccessLevel(AccessLevel accessLevel);

    void removeAccessLevel(AccessLevel accessLevel);
}
