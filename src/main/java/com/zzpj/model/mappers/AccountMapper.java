package com.zzpj.model.mappers;

import com.zzpj.model.DTOs.AccountNoRecipesDTO;
import com.zzpj.model.DTOs.AccountRecipesDTO;
import com.zzpj.model.entities.AccessLevel;
import com.zzpj.model.entities.Account;

import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountNoRecipesDTO entityToAdminDTO(Account account) {
        AccountNoRecipesDTO accountNoRecipesDTO = new AccountNoRecipesDTO();

        accountNoRecipesDTO.setLogin(account.getLogin());
        accountNoRecipesDTO.setPassword("");
        accountNoRecipesDTO.setEmail(account.getEmail());
        accountNoRecipesDTO.setAccessLevel(AccessLevelMapper.entityToDTO(account.getAccessLevel()));
        accountNoRecipesDTO.setActive(account.getActive());

        return accountNoRecipesDTO;
    }

    public static AccountRecipesDTO entityToRecipesDTO(Account account) {
        AccountRecipesDTO accountRecipesDTO = new AccountRecipesDTO();

        accountRecipesDTO.setLogin(account.getLogin());
        accountRecipesDTO.setEmail(account.getEmail());
        accountRecipesDTO.setFavouriteRecipes(account.getFavouriteRecipes().stream()
                .map(RecipeMapper::entityToGeneralDTO)
                .collect(Collectors.toList()));

        return accountRecipesDTO;
    }

    public static Account noRecipesDTOWithAccessLevelToEntity(AccountNoRecipesDTO accountNoRecipesDTO, AccessLevel accessLevel) {
        Account account = new Account();

        account.setLogin(accountNoRecipesDTO.getLogin());
        account.setPassword(accountNoRecipesDTO.getPassword());
        account.setEmail(accountNoRecipesDTO.getEmail());
        account.setAccessLevel(accessLevel);
        account.setActive(accountNoRecipesDTO.getActive());

        return account;
    }

    public static Account recipesDTOWithAccessLevelToEntity(AccountRecipesDTO accountRecipesDTO, AccessLevel accessLevel) {
        Account account = new Account();

        account.setLogin(accountRecipesDTO.getLogin());
        account.setEmail(accountRecipesDTO.getEmail());
        account.setFavouriteRecipes(accountRecipesDTO.getFavouriteRecipes().stream()
                .map(RecipeMapper::generalDTOToEntity)
                .collect(Collectors.toList()));
        return account;
    }
}
