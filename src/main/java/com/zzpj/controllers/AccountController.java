package com.zzpj.controllers;

import com.zzpj.exceptions.AccountDoesNotExistException;
import com.zzpj.exceptions.EmailAlreadyExistsException;
import com.zzpj.exceptions.LoginAlreadyExistsException;
import com.zzpj.model.DTOs.AccountNoRecipesDTO;
import com.zzpj.model.DTOs.AccountRecipesDTO;
import com.zzpj.model.entities.AccessLevel;
import com.zzpj.model.mappers.AccountMapper;
import com.zzpj.services.interfaces.AccessLevelServiceInterface;
import com.zzpj.services.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    private final AccountServiceInterface accountService;

    private final AccessLevelServiceInterface accessLevelService;

    @Autowired
    public AccountController(AccountServiceInterface accountService, AccessLevelServiceInterface accessLevelService) {
        this.accountService = accountService;
        this.accessLevelService = accessLevelService;
    }
    // TODO Wszystko opakować wyjątkami

    @PostMapping(path = "/accounts", consumes = "application/json")
    public ResponseEntity<AccountNoRecipesDTO> createAccount(@RequestBody AccountNoRecipesDTO accountDTO) {
        try {
            AccessLevel accessLevel = accessLevelService.getAccessLevelByName(accountDTO.getAccessLevel());
            accountService.createAccount(AccountMapper.noRecipesDTOWithAccessLevelToEntity(accountDTO, accessLevel));
        } catch (LoginAlreadyExistsException | EmailAlreadyExistsException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // TODO opakować wyjątkami

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<AccountNoRecipesDTO> registerAccount(@RequestBody AccountNoRecipesDTO accountDTO) {
        if (!accountDTO.getAccessLevel().equals(AccessLevel.USER)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            AccessLevel accessLevel = accessLevelService.getAccessLevelByName(accountDTO.getAccessLevel());
            accountService.createAccount(AccountMapper.noRecipesDTOWithAccessLevelToEntity(accountDTO, accessLevel));
        } catch (LoginAlreadyExistsException | EmailAlreadyExistsException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // TODO opakować wyjątkami

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/accounts/{login}", produces = "application/json")
    public ResponseEntity<AccountNoRecipesDTO> getAccount(@PathVariable String login) {
        AccountNoRecipesDTO accountNoRecipesDTO;
        try {
            accountNoRecipesDTO = AccountMapper.entityToAdminDTO(accountService.getAccountByLogin(login));
        } catch (AccountDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(accountNoRecipesDTO);
    }

    @GetMapping(path = "/accounts/recipes/{login}", produces = "application/json")
    public ResponseEntity<AccountRecipesDTO> getAccountRecipes(@PathVariable String login) {
        AccountRecipesDTO accountRecipesDTO;
        try {
            accountRecipesDTO = AccountMapper.entityToRecipesDTO(accountService.getAccountByLogin(login));
        } catch (AccountDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(accountRecipesDTO);
    }

    @GetMapping(path = "/accounts", produces = "application/json")
    public ResponseEntity<?> getAllAccounts() {
        //TODO
        List<AccountNoRecipesDTO> accounts = accountService.getAllAccounts().stream()
                .map(AccountMapper::entityToAdminDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(accounts);
    }

    @GetMapping(path = "/accounts/recipes", produces = "application/json")
    public ResponseEntity<?> getAllAccountsRecipes() {
        List<AccountRecipesDTO> accountRecipesDTOs = accountService.getAllAccounts().stream()
                .map(AccountMapper::entityToRecipesDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(accountRecipesDTOs);
    }


    @PutMapping(path = "/accounts/{login}", consumes = "application/json")
    public ResponseEntity<?> updateAccount(@PathVariable String login, @RequestBody AccountNoRecipesDTO accountDTO) {
        try {
            AccessLevel accessLevel = accessLevelService.getAccessLevelByName(accountDTO.getAccessLevel());
            accountService.updateAccount(login, AccountMapper.noRecipesDTOWithAccessLevelToEntity(accountDTO, accessLevel));
        } catch (AccountDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "accounts/activate/{login}")
    public ResponseEntity<?> activateAccount(@PathVariable String login) {
        try {
            accountService.activateAccount(login);
        } catch (AccountDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "accounts/deactivate/{login}")
    public ResponseEntity<?> deactivateAccount(@PathVariable String login) {
        try {
            accountService.deactivateAccount(login);
        } catch (AccountDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }
}
