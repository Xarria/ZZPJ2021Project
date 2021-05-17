package com.zzpj.services;

import com.zzpj.exceptions.AccountDoesNotExistException;
import com.zzpj.exceptions.EmailAlreadyExistsException;
import com.zzpj.exceptions.LoginAlreadyExistsException;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.AccountPrincipal;
import com.zzpj.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService accountService;
    @Mock
    private final Account account = new Account();
    @Mock
    private final Account newAccount = new Account();

    private final String login = "login";
    private final String email = "email@email.com";
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
    }

    @Test
    void activateAccount() {
    }

    @Test
    void deactivateAccount() {
    }
}
