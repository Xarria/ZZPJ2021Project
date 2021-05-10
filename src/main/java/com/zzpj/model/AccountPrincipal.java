package com.zzpj.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class AccountPrincipal implements UserDetails {

    private Account account;

    public AccountPrincipal(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(account.getAccessLevel());
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return account.getActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.getActive();
    }

    @Override
    public boolean isEnabled() {
        return account.getActive();
    }
}
