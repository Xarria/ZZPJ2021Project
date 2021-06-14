package com.zzpj.model.mappers;

import com.zzpj.model.DTOs.AccountNoRecipesDTO;
import com.zzpj.model.DTOs.AccountRecipesDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.AccessLevel;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountMapperTest {

    private Account account;
    private AccountNoRecipesDTO accountNoRecipesDTO;
    private AccountRecipesDTO accountRecipesDTO;
    private AccessLevel accessLevel;
    private final Long id = 1L;
    private final String login = "login";
    private final String password = "password?";
    private final String email = "definitely@real.mail";
    private final List<Recipe> favouriteRecipes = new ArrayList<>();
    private final List<RecipeGeneralDTO> favouriteRecipesGeneralDTO = new ArrayList<>();
    private final String accessLevelString = "user";
    private final Boolean active = true;


    Account createAccount() {
        Account account = new Account();
        account.setId(id);
        account.setLogin(login);
        account.setPassword(password);
        account.setEmail(email);
        account.setFavouriteRecipes(favouriteRecipes);
        account.setAccessLevel(accessLevel);
        account.setActive(active);
        return account;
    }

    AccountNoRecipesDTO createAccountNoRecipesDTO() {
        return new AccountNoRecipesDTO(login, "", email, accessLevel.toString(), active);
    }

    AccountRecipesDTO createAccountRecipesDTO() {
        return new AccountRecipesDTO(login, email, favouriteRecipesGeneralDTO);
    }

    AccessLevel createAccessLevel() {
        return new AccessLevel(id, accessLevelString);
    }

    @BeforeEach
    void init() {
        accessLevel = createAccessLevel();
        account = createAccount();
        accountNoRecipesDTO = createAccountNoRecipesDTO();
        accountRecipesDTO = createAccountRecipesDTO();
    }

    @Test
    void entityToAdminDTO() {
        AccountNoRecipesDTO newAccountNoRecipesDTO = AccountMapper.entityToAdminDTO(account);

        assertEquals(login, newAccountNoRecipesDTO.getLogin());
        assertEquals("", newAccountNoRecipesDTO.getPassword());
        assertEquals(email, newAccountNoRecipesDTO.getEmail());
        assertEquals(accessLevel.getName(), newAccountNoRecipesDTO.getAccessLevel());
        assertEquals(active, newAccountNoRecipesDTO.getActive());
    }

    @Test
    void entityToRecipesDTO() {
        AccountRecipesDTO newAccountRecipesDTO = AccountMapper.entityToRecipesDTO(account);

        assertEquals(login, newAccountRecipesDTO.getLogin());
        assertEquals(email, newAccountRecipesDTO.getEmail());
        assertNotNull(newAccountRecipesDTO.getFavouriteRecipes());
    }

    @Test
    void noRecipesDTOWithAccessLevelToEntity() {
        Account newAccount = AccountMapper.noRecipesDTOWithAccessLevelToEntity(accountNoRecipesDTO, accessLevel);

        assertEquals(login, newAccount.getLogin());
        assertEquals("", newAccount.getPassword());
        assertEquals(email, newAccount.getEmail());
        assertEquals(accessLevel, newAccount.getAccessLevel());
        assertEquals(active, newAccount.getActive());
    }


    @Test
    void recipesDTOWithAccessLevelToEntity() {
        Account newAccount = AccountMapper.recipesDTOWithAccessLevelToEntity(accountRecipesDTO, accessLevel);

        assertEquals(login, newAccount.getLogin());
        assertEquals(email, newAccount.getEmail());
        assertNotNull(newAccount.getFavouriteRecipes());

    }
}
