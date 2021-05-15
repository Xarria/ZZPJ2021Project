package com.zzpj.services;

import com.zzpj.DTOs.AccountDTO;
import com.zzpj.model.Account;
import com.zzpj.model.AccountPrincipal;
import com.zzpj.repository.AccountRepository;
import com.zzpj.services.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService, AccountServiceInterface {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAll().stream()
                .filter(acc -> acc.getLogin().equals(username))
                .findFirst()
                .orElseThrow( () -> new UsernameNotFoundException("Account with login " + username + " was not found."));
        return new AccountPrincipal(account);
    }


    @Override
    public void createAccount(Account account) {

    }

    @Override
    public AccountDTO getAccountByLogin(String login) {
        return null;
    }

    @Override
    public AccountDTO getAllAccounts() {
        return null;
    }

    @Override
    public void updateAccount(String login, Account updatedAccount) {

    }

    @Override
    public void activateAccount(String login) {

    }

    @Override
    public void deactivateAccount(String login) {

    }
}
