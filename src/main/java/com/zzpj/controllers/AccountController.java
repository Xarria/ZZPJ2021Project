package com.zzpj.controllers;

import com.zzpj.DTOs.AccountDTO;
import com.zzpj.mappers.AccountMapper;
import com.zzpj.model.Account;
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
    @PostMapping(path = "/accounts", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO account) {
        //TODO crete account (dto to entity mapper required)
        Account createdAccount = new Account();
        return ResponseEntity.created(URI.create("/accounts/" + createdAccount.getLogin())).build();
    }

    // read
    @GetMapping(path = "/accounts/{login}", produces = "application/json")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable String login) {
        AccountDTO account = AccountMapper.entityToDTO(accountService.getAccountByLogin(login));
        return ResponseEntity.ok(account);
    }

    @GetMapping(path = "/accounts", produces = "application/json")
    public ResponseEntity<?> getAllAccounts() {
        //TODO
        List<AccountDTO> accounts = accountService.getAllAccounts()
                .stream().map(AccountMapper::entityToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(accounts);
    }

    // update
    @PutMapping(path = "/accounts/{login}", consumes = "application/json")
    public ResponseEntity<?> updateAccount(@PathVariable String login, @RequestBody AccountDTO account) {
        //TODO update account (dto to entity mapper required)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // activity

    // access levels
}
