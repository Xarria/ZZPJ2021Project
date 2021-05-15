package com.zzpj.mappers;

import com.zzpj.DTOs.AccountAccessLevelDTO;
import com.zzpj.model.Account;

import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountAccessLevelDTO entityToDTO(Account account){
        AccountAccessLevelDTO accountDTO = new AccountAccessLevelDTO();
        accountDTO.setLogin(account.getLogin());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setFavouriteRecipes(account.getFavouriteRecipes().stream()
                .map(RecipeMapper::entityToDTO)
                .collect(Collectors.toList()));
        accountDTO.setAccessLevel(AccessLevelMapper.entityToDTO(account.getAccessLevel()));
        accountDTO.setActive(account.getActive());

        return accountDTO;
    }
}
