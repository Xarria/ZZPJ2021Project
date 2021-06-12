package com.zzpj.controllers;

import com.zzpj.exceptions.AccountDoesNotExistException;
import com.zzpj.exceptions.RecipeDoesNotExistException;
import com.zzpj.model.DTOs.IngredientDTO;
import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.Account;
import com.zzpj.model.mappers.IngredientsMapper;
import com.zzpj.model.mappers.RecipeMapper;
import com.zzpj.services.interfaces.AccountServiceInterface;
import com.zzpj.services.interfaces.RecipeServiceInterface;
import com.zzpj.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecipeController {

    private final RecipeServiceInterface recipeService;
    private final AccountServiceInterface accountService;

    @Autowired
    public RecipeController(RecipeServiceInterface recipeService, AccountServiceInterface accountService) {
        this.recipeService = recipeService;
        this.accountService = accountService;
    }

    @PostMapping(path = "/recipes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDetailsDTO> createRecipe(@RequestBody RecipeDetailsDTO recipe) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        recipe.setAuthorLogin(authentication.getName());
        recipeService.createRecipe(RecipeMapper.detailsDTOtoEntity(recipe));
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/recipes/{id}", produces = "application/json")
    public ResponseEntity<RecipeDetailsDTO> getRecipeById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(RecipeMapper.entityToDetailsDTO(recipeService.getRecipeById(id)));
        }
        catch (RecipeDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = "/recipes", produces = "application/json")
    public ResponseEntity<List<RecipeGeneralDTO>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes().stream().
                map(RecipeMapper::entityToGeneralDTO).
                collect(Collectors.toList()));
    }

    @GetMapping(path = "/recipes/recommendation/like", produces = "application/json")
    public ResponseEntity<List<RecipeGeneralDTO>> getRecommendationBasedOnLikings(@RequestBody List<String> unwantedTags) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            Account account = accountService.getAccountByLogin(authentication.getName());
            return ResponseEntity.ok(recipeService.getRecommendationBasedOnLikings(account, unwantedTags).stream().
                    map(RecipeMapper::entityToGeneralDTO).
                    collect(Collectors.toList()));
        } catch (AccountDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping(path = "/recipes/account/{login}", produces = "application/json")
    public ResponseEntity<List<RecipeGeneralDTO>> getAllRecipesForAccount(@PathVariable String login) {
        return ResponseEntity.ok(recipeService.getAllRecipesForAccount(login).stream().
                map(RecipeMapper::entityToGeneralDTO).
                collect(Collectors.toList()));
    }

    @GetMapping(path = "/recipes/favourite/{login}", produces = "application/json")
    public ResponseEntity<List<RecipeGeneralDTO>> getFavouriteRecipesForAccount(@PathVariable String login) {
        return ResponseEntity.ok(recipeService.getFavouriteRecipesForAccount(login).stream().
                map(RecipeMapper::entityToGeneralDTO).
                collect(Collectors.toList()));
    }

    @DeleteMapping(path = "/recipes/{id}", produces = "application/json")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        try{
            recipeService.deleteRecipe(id);
            return ResponseEntity.ok().build();
        }
        catch (RecipeDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/recipes/{id}", consumes = "application/json")
    public ResponseEntity<?> updateRecipe(@PathVariable Long id, @RequestBody RecipeDetailsDTO updatedRecipe) {
        try {
            recipeService.updateRecipe(id, RecipeMapper.detailsDTOtoEntity(updatedRecipe));
            return ResponseEntity.ok().build();
        } catch (RecipeDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/recipes/ingredients/{id}", consumes = "application/json")
    public ResponseEntity<?> addIngredient(@PathVariable Long id, @RequestBody IngredientDTO ingredientDTO) {
        try {
            recipeService.addIngredient(id, IngredientsMapper.dtoToEntity(ingredientDTO));
            return ResponseEntity.ok().build();
        }
        catch (RecipeDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = "/recipes/save/{id}")
    public ResponseEntity<?> sendRecipeByMail(@PathVariable Long id)  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            Account account = accountService.getAccountByLogin(authentication.getName());
            String text = recipeService.sendRecipeByMail(id);
            EmailSender.sendEmail(account.getLogin(), account.getEmail(), "RECIPE", text);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (RecipeDoesNotExistException | AccountDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = "/recipes/shopping_list", consumes = "application/json")
    public ResponseEntity<?> sendShoppingListByMail(@RequestBody List<Long> recipesId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Account account = accountService.getAccountByLogin(authentication.getName());
            String text = recipeService.getShoppingList(recipesId);
            EmailSender.sendEmail(account.getLogin(), account.getEmail(), "SHOPPING LIST", text);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (AccountDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/recipes/ratings/{id}", consumes = "application/text")
    public ResponseEntity<?> addRatingToRecipe(@PathVariable Long id, @RequestBody float rating) {
        try {
            recipeService.addRatingToRecipe(id, rating);
            return ResponseEntity.ok().build();
        }
        catch (RecipeDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/recipes/favourite/add/{id}",
        consumes = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> addRecipeToFavourites(@PathVariable Long id) {
        recipeService.addRecipeToFavourites(SecurityContextHolder.getContext().getAuthentication().getName(), id);
        return ResponseEntity.ok().build();
    }
}
