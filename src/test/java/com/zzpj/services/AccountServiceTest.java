package com.zzpj.services;

import com.zzpj.exceptions.AccountDoesNotExistException;
import com.zzpj.exceptions.EmailAlreadyExistsException;
import com.zzpj.exceptions.LoginAlreadyExistsException;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.AccountPrincipal;
import com.zzpj.repositories.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.token.Sha512DigestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService accountService;
    @Mock
    private final Account account = new Account();
    @Mock
    private final Account newAccount = new Account();
    @Spy
    private final Account updatedAccount = new Account();

    private final String login = "login";
    private final String email = "email@email.com";
    private final String password = "password";
    private final String newLogin = "newLogin";
    private final String newPassword = "newPassword";
    private final String newEmail = "newEmail@email.com";
    private final List<Account> accounts = new ArrayList<>();

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(accountRepository.findAll()).thenReturn(accounts);
        Mockito.when(account.getLogin()).thenReturn(login);
        Mockito.when(account.getEmail()).thenReturn(email);
        Mockito.when(account.getPassword()).thenReturn(password);
        accounts.add(account);
    }

    @Test
    void loadUserByUsername() {
        Mockito.when(account.getLogin()).thenReturn(login);
        Assertions.assertEquals(new AccountPrincipal(account), accountService.loadUserByUsername(login));
    }

    @Test
    void createAccount() {
        Mockito.doAnswer(invocation -> {
            accounts.add(invocation.getArgument(0));
            return null;
        }).when(accountRepository).save(newAccount);

        Mockito.when(newAccount.getLogin()).thenReturn(newLogin);
        Mockito.when(newAccount.getEmail()).thenReturn(newEmail);
        Mockito.when(newAccount.getPassword()).thenReturn(newPassword);

        Assertions.assertEquals(1, accounts.size());
        Assertions.assertDoesNotThrow(() -> accountService.createAccount(newAccount));
        Mockito.verify(accountRepository).save(newAccount);
        Assertions.assertEquals(2, accounts.size());
        Assertions.assertEquals(newAccount, accounts.get(1));
        Assertions.assertEquals(newLogin, accounts.get(1).getLogin());
        Assertions.assertEquals(newEmail, accounts.get(1).getEmail());
    }

    @Test
    void createAccountExistingLoginException() {
        Mockito.when(newAccount.getLogin()).thenReturn(login);
        Mockito.when(newAccount.getEmail()).thenReturn(newEmail);
        Assertions.assertThrows(LoginAlreadyExistsException.class, () -> accountService.createAccount(newAccount));
    }

    @Test
    void createAccountExistingEmailException() {
        Mockito.when(newAccount.getLogin()).thenReturn(newLogin);
        Mockito.when(newAccount.getEmail()).thenReturn(email);
        Assertions.assertThrows(EmailAlreadyExistsException.class, () -> accountService.createAccount(newAccount));
    }

    @Test
    void getAccountByLogin() {
        Mockito.when(accountRepository.findByLogin(login)).thenReturn(account);
        Mockito.when(account.getLogin()).thenReturn(login);

        Assertions.assertDoesNotThrow(() -> accountService.getAccountByLogin(login));

        Assertions.assertDoesNotThrow(
                () -> Assertions.assertEquals(account, accountService.getAccountByLogin(login))
        );
        Assertions.assertDoesNotThrow(
                () -> Assertions.assertEquals(accounts.get(0), accountService.getAccountByLogin(login))
        );
    }

    @Test
    void getAccountByLoginException() {
        Assertions.assertThrows(AccountDoesNotExistException.class, () -> accountService.getAccountByLogin(newLogin));
    }

    @Test
    void getAllAccounts() {
        Assertions.assertEquals(accounts, accountService.getAllAccounts());
    }

    @Test
    void updateAccount() {
        updatedAccount.setPassword(Sha512DigestUtils.shaHex(password));
        updatedAccount.setEmail(email);

        Mockito.doAnswer(invocation -> {
            Account account = invocation.getArgument(0);
            account.setPassword(Sha512DigestUtils.shaHex(newPassword));
            account.setEmail(newEmail);
            return null;
        }).when(accountRepository).save(any());

        Mockito.when(accountRepository.findByLogin(login)).thenReturn(updatedAccount);

        Assertions.assertEquals(Sha512DigestUtils.shaHex(password), updatedAccount.getPassword());
        Assertions.assertEquals(email, updatedAccount.getEmail());
        Assertions.assertDoesNotThrow(() -> accountService.updateAccount(login, updatedAccount));
        Mockito.verify(accountRepository).save(updatedAccount);
        Assertions.assertEquals(Sha512DigestUtils.shaHex(newPassword), updatedAccount.getPassword());
        Assertions.assertEquals(newEmail, updatedAccount.getEmail());
    }

    @Test
    void activateAccount() {
        updatedAccount.setActive(false);

        Mockito.doAnswer(invocation -> {
            Account account = invocation.getArgument(0);
            account.setActive(true);
            return null;
        }).when(accountRepository).save(any());

        Mockito.when(accountRepository.findByLogin(login)).thenReturn(updatedAccount);

        Assertions.assertFalse(updatedAccount.getActive());
        Assertions.assertDoesNotThrow(() -> accountService.activateAccount(login));
        Mockito.verify(accountRepository).save(updatedAccount);
        Assertions.assertTrue(updatedAccount.getActive());
    }

    @Test
    void deactivateAccount() {
        updatedAccount.setActive(true);

        Mockito.doAnswer(invocation -> {
            Account account = invocation.getArgument(0);
            account.setActive(false);
            return null;
        }).when(accountRepository).save(any());

        Mockito.when(accountRepository.findByLogin(login)).thenReturn(updatedAccount);

        Assertions.assertTrue(updatedAccount.getActive());
        Assertions.assertDoesNotThrow(() -> accountService.activateAccount(login));
        Mockito.verify(accountRepository).save(updatedAccount);
        Assertions.assertFalse(updatedAccount.getActive());
    }
}
