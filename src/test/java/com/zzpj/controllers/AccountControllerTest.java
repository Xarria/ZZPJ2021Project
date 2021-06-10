package com.zzpj.controllers;

import com.zzpj.exceptions.AccountDoesNotExistException;
import com.zzpj.exceptions.EmailAlreadyExistsException;
import com.zzpj.exceptions.LoginAlreadyExistsException;
import com.zzpj.model.DTOs.AccountNoRecipesDTO;
import com.zzpj.model.DTOs.AccountRecipesDTO;
import com.zzpj.model.entities.AccessLevel;
import com.zzpj.model.entities.Account;
import com.zzpj.model.mappers.AccountMapper;
import com.zzpj.services.interfaces.AccessLevelServiceInterface;
import com.zzpj.services.interfaces.AccountServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Spy
    private final Account account = new Account();
    @Spy
    private final Account account1 = new Account();
    private final String login = "Login";
    private final String login1 = "Login1";
    private final String email = "email@email.com";
    private final String newEmail = "newEmail@email.com";
    private final String accessLevelName = "user";
    private final List<Account> accounts = new ArrayList<>();
    @Spy
    private final AccessLevel accessLevel = new AccessLevel();
    @Mock
    private AccountServiceInterface accountService;
    @Mock
    private AccessLevelServiceInterface accessLevelService;
    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
        when(accessLevel.getName()).thenReturn(accessLevelName);
        when(account.getLogin()).thenReturn(login);
        when(account.getEmail()).thenReturn(email);
        when(account.getAccessLevel()).thenReturn(accessLevel);
        when(account.getActive()).thenReturn(true);
        account1.setLogin(login1);
        account1.setEmail(email);
        account1.setAccessLevel(accessLevel);
        account1.setActive(true);
        accounts.add(account1);
    }

    @Test
    void createAccount() throws LoginAlreadyExistsException, EmailAlreadyExistsException {
        Mockito.doAnswer(invocationOnMock -> {
            accounts.add(invocationOnMock.getArgument(0));
            return null;
        }).when(accountService).createAccount(any());
        when(accessLevelService.getAccessLevelByName(accessLevelName)).thenReturn(accessLevel);

        assertEquals(1, accounts.size());
        assertDoesNotThrow(() -> accountController.createAccount(AccountMapper.entityToAdminDTO(account)));
        assertEquals(2, accounts.size());
        assertEquals(HttpStatus.OK, (accountController.createAccount(AccountMapper.entityToAdminDTO(account)).getStatusCode()));

        verify(accountService, times(2)).createAccount(any());
        verify(accessLevelService, times(2)).getAccessLevelByName(any());
    }

    @Test
    void registerAccount() throws LoginAlreadyExistsException, EmailAlreadyExistsException {
        Mockito.doAnswer(invocationOnMock -> {
            accounts.add(invocationOnMock.getArgument(0));
            return null;
        }).when(accountService).createAccount(any());
        when(accessLevelService.getAccessLevelByName(accessLevelName)).thenReturn(accessLevel);

        assertEquals(1, accounts.size());
        assertDoesNotThrow(() -> accountController.registerAccount(AccountMapper.entityToAdminDTO(account)));
        assertEquals(2, accounts.size());
        assertEquals(HttpStatus.OK, (accountController.registerAccount(AccountMapper.entityToAdminDTO(account)).getStatusCode()));

        verify(accountService, times(2)).createAccount(any());
        verify(accessLevelService, times(2)).getAccessLevelByName(any());
    }

    @Test
    void getAccount() throws AccountDoesNotExistException {
        when(accountService.getAccountByLogin(login)).thenReturn(account);
        assertDoesNotThrow(() -> accountController.getAccount(login));
        assertEquals(AccountMapper.entityToAdminDTO(account), accountController.getAccount(login).getBody());
        assertEquals(HttpStatus.OK, accountController.getAccount(login).getStatusCode());
        verify(accountService, times(3)).getAccountByLogin(login);
    }

    @Test
    void getAccountRecipes() throws AccountDoesNotExistException {
        when(accountService.getAccountByLogin(login)).thenReturn(account);
        assertDoesNotThrow(() -> accountController.getAccountRecipes(login));
        assertEquals(AccountMapper.entityToRecipesDTO(account), accountController.getAccountRecipes(login).getBody());
        assertEquals(HttpStatus.OK, accountController.getAccountRecipes(login).getStatusCode());
        verify(accountService, times(3)).getAccountByLogin(login);
    }

    @Test
    void getAllAccounts() {
        when(accountService.getAllAccounts()).thenReturn(accounts);
        assertDoesNotThrow(() -> accountController.getAllAccounts());
        List<AccountNoRecipesDTO> accountNoRecipesDTOList = accounts.stream()
                .map(AccountMapper::entityToAdminDTO).collect(Collectors.toList());
        assertEquals(accountNoRecipesDTOList, accountController.getAllAccounts().getBody());
        assertEquals(HttpStatus.OK, accountController.getAllAccounts().getStatusCode());
        verify(accountService, times(3)).getAllAccounts();
    }

    @Test
    void getAllAccountsRecipes() {
        when(accountService.getAllAccounts()).thenReturn(accounts);
        assertDoesNotThrow(() -> accountController.getAllAccountsRecipes());
        List<AccountRecipesDTO> accountRecipesDTOList = accounts.stream()
                .map(AccountMapper::entityToRecipesDTO).collect(Collectors.toList());
        assertEquals(accountRecipesDTOList, accountController.getAllAccountsRecipes().getBody());
        assertEquals(HttpStatus.OK, accountController.getAllAccountsRecipes().getStatusCode());
        verify(accountService, times(3)).getAllAccounts();
    }

    @Test
    void updateAccount() throws AccountDoesNotExistException {
        doAnswer(invocationOnMock -> {
            account1.setEmail(newEmail);
            return null;
        }).when(accountService).updateAccount(any(), any());

        assertEquals(email, accounts.get(0).getEmail());
        assertDoesNotThrow(() -> accountController.updateAccount(login1, AccountMapper.entityToAdminDTO(account1)));
        assertEquals(newEmail, accounts.get(0).getEmail());
        verify(accountService).updateAccount(any(), any());
        verify(accessLevelService).getAccessLevelByName(accessLevelName);
    }

    @Test
    void activateAccount() {

    }

    @Test
    void deactivateAccount() {
    }
}
