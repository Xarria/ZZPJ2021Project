package com.zzpj.services.interfaces;

import com.zzpj.DTOs.AccountDTO;
import com.zzpj.model.Account;

public interface AccountServiceInterface {

    void createAccount(Account account);

    AccountDTO getAccountByLogin(String login);

    AccountDTO getAllAccounts();

    void updateAccount(String login, Account updatedAccount);

    void activateAccount(String login);

    void deactivateAccount(String login);
}
