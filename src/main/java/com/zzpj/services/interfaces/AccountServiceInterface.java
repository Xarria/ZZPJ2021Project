package com.zzpj.services.interfaces;

import com.zzpj.DTOs.AccountAccessLevelDTO;
import com.zzpj.model.Account;

public interface AccountServiceInterface {

    void createAccount(Account account);

    AccountAccessLevelDTO getAccountByLogin(String login);

    AccountAccessLevelDTO getAllAccounts();

    void updateAccount(String login, Account updatedAccount);

    void activateAccount(String login);

    void deactivateAccount(String login);
}
