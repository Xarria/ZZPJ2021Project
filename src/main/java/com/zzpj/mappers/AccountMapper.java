package com.zzpj.mappers;

import com.zzpj.DTOs.AccountAccessLevelDTO;
import com.zzpj.model.Account;

public class AccountMapper {

    public static AccountAccessLevelDTO entityToDTO(Account account){
        AccountAccessLevelDTO accountDTO = new AccountAccessLevelDTO();
        accountDTO.setLogin(account.getLogin());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setEmail(account.getEmail());
        // TODO Przepuścić favourite recipes przez Recipe mapper i przypisać do pola
        //accountDTO.setFavouriteRecipes(account.getFavouriteRecipes());
        accountDTO.setAccessLevel(AccessLevelMapper.entityToDTO(account.getAccessLevel()));
        accountDTO.setActive(account.getActive());

        return accountDTO;
    }
}
