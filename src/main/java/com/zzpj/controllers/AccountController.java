package com.zzpj.controllers;

import com.zzpj.exceptions.EmailAlreadyExistsException;
import com.zzpj.exceptions.LoginAlreadyExistsException;
import com.zzpj.model.DTOs.AccountNoRecipesDTO;
import com.zzpj.model.DTOs.AccountRecipesDTO;
import com.zzpj.model.mappers.AccountMapper;
import com.zzpj.model.entities.Account;
import com.zzpj.services.AccountService;
import com.zzpj.services.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    private final AccountServiceInterface accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // create
    @PostMapping(path = "/accounts", consumes = "application/json")
    public ResponseEntity<AccountNoRecipesDTO> createAccount(@RequestBody AccountNoRecipesDTO account) {
        try {
            accountService.createAccount(AccountMapper.adminDtoToEntity(account));
        } catch (LoginAlreadyExistsException | EmailAlreadyExistsException ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    // read
    @GetMapping(path = "/accounts/{login}", produces = "application/json")
    public ResponseEntity<AccountNoRecipesDTO> getAccount(@PathVariable String login) {
        AccountNoRecipesDTO accountNoRecipesDTO = AccountMapper.entityToAdminDTO(accountService.getAccountByLogin(login));
        return ResponseEntity.ok(accountNoRecipesDTO);
    }

    @GetMapping(path = "/accounts/r/{login}", produces = "application/json")
    public ResponseEntity<AccountRecipesDTO> getAccountRecipes(@PathVariable String login) {
        AccountRecipesDTO accountRecipesDTO = AccountMapper.entityToRecipesDTO(accountService.getAccountByLogin(login));
        return ResponseEntity.ok(accountRecipesDTO);
    }

    @GetMapping(path = "/accounts", produces = "application/json")
    public ResponseEntity<?> getAllAccounts() {
        //TODO
        List<AccountNoRecipesDTO> accounts = accountService.getAllAccounts()
                .stream().map(AccountMapper::entityToAdminDTO).collect(Collectors.toList());

        return ResponseEntity.ok(accounts);
    }

    @GetMapping(path = "/accounts/r", produces = "application/json")
    public ResponseEntity<?> getAllAccountsRecipes() {
        List<AccountRecipesDTO> accountRecipesDTOs = accountService.getAllAccounts().stream()
                .map(AccountMapper::entityToRecipesDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(accountRecipesDTOs);
    }

    // update
    @PutMapping(path = "/accounts/{login}", consumes = "application/json")
    public ResponseEntity<?> updateAccount(@PathVariable String login, @RequestBody AccountNoRecipesDTO account) {
        //TODO update account (dto to entity mapper required)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // activity

    // access levels
}
