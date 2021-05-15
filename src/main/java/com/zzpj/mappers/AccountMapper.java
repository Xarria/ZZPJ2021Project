package com.zzpj.mappers;

import com.zzpj.DTOs.AccountDTO;
import com.zzpj.model.Account;

import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountDTO entityToDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
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
