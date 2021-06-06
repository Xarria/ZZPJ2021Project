package com.zzpj.controllers;

import com.zzpj.services.interfaces.AccessLevelServiceInterface;
import com.zzpj.services.interfaces.AccountServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AccountControllerTest {

    @Mock
    private AccountServiceInterface accountService;
    @Mock
    private AccessLevelServiceInterface accessLevelService;
    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount() {
    }

    @Test
    void registerAccount() {
    }

    @Test
    void getAccount() {

    }

    @Test
    void getAccountRecipes() {
    }

    @Test
    void getAllAccounts() {
    }

    @Test
    void getAllAccountsRecipes() {
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
