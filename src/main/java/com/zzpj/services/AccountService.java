package com.zzpj.services;

import com.zzpj.exceptions.EmailAlreadyExistsException;
import com.zzpj.exceptions.LoginAlreadyExistsException;
import com.zzpj.model.entities.AccessLevel;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.AccountPrincipal;
import com.zzpj.repository.AccountRepository;
import com.zzpj.services.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new UsernameNotFoundException("Account with login " + username + " was not found."));
        return new AccountPrincipal(account);
    }


    @Override
    public void createAccount(Account account) throws LoginAlreadyExistsException, EmailAlreadyExistsException {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.stream().anyMatch(a -> account.getLogin().equals(a.getLogin()))) {
            throw new LoginAlreadyExistsException("Account with such login already exists");
        } else if (accounts.stream().anyMatch(acc -> account.getEmail().equals(acc.getEmail()))) {
            throw new EmailAlreadyExistsException("Account with such e-mail already exists");
        }
        account.setPassword(Sha512DigestUtils.shaHex(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public Account getAccountByLogin(String login) {
        return accountRepository.findAll().stream()
                .filter(acc -> acc.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Account with login " + login + " was not found."));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
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

    @Override
    public void addAccessLevel(AccessLevel accessLevel) {

    }

    @Override
    public void removeAccessLevel(AccessLevel accessLevel) {

    }
}
