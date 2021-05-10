package com.zzpj.controllers;

import com.zzpj.DTOs.UserCredentials;
import com.zzpj.security.JWTUtils;
import com.zzpj.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final AccountService accountService;

    private final JWTUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AccountService accountService, JWTUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody UserCredentials userCredentials) {
        try {
            userCredentials.setPassword(Sha512DigestUtils.shaHex(userCredentials.getPassword()));
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword()));
        } catch (BadCredentialsException | LockedException | DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

        UserDetails userDetails = accountService.loadUserByUsername(userCredentials.getUsername());

        return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
    }
}
