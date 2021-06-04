package com.zzpj.controllers;

import com.zzpj.exceptions.AccountDoesNotExistException;
import com.zzpj.exceptions.RecipeDoesNotExistException;
import com.zzpj.model.DTOs.IngredientDTO;
import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.AccessLevel;
import com.zzpj.model.mappers.IngredientsMapper;
import com.zzpj.model.mappers.RecipeMapper;
import com.zzpj.services.interfaces.AccountServiceInterface;
import com.zzpj.services.interfaces.RecipeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(path = "/recipe", consumes = "application/json")
    public ResponseEntity<RecipeDetailsDTO> createRecipe(@RequestBody RecipeDetailsDTO recipe) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            AccessLevel accessLevel = accountService.getAccountByLogin(authentication.getName()).getAccessLevel();
            recipeService.createRecipe(RecipeMapper.detailsDTOtoEntity(recipe, accessLevel));
            return ResponseEntity.ok().build();
        }
        catch (AccountDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(path = "/recipe/{id}", produces = "application/json")
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

    @GetMapping(path = "/recipes/remove/{id}", produces = "application/json")
    public ResponseEntity<?> deleteRecipe(Long id) {
        try{
            recipeService.deleteRecipe(id);
            return ResponseEntity.ok().build();
        }
        catch (RecipeDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/recipe/{id}", consumes = "application/json")
    public ResponseEntity<?> updateRecipe(@PathVariable Long id, @RequestBody RecipeDetailsDTO updatedRecipe) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            AccessLevel accessLevel = accountService.getAccountByLogin(authentication.getName()).getAccessLevel();
            recipeService.updateRecipe(id, RecipeMapper.detailsDTOtoEntity(updatedRecipe, accessLevel));
            return ResponseEntity.ok().build();
        }
        catch (AccountDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (RecipeDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/recipe/ingredients/{id}", consumes = "application/json")
    public ResponseEntity<?> addIngredient(@PathVariable Long id, @RequestBody IngredientDTO ingredientDTO) {
        try {
            recipeService.addIngredient(id, IngredientsMapper.dtoToEntity(ingredientDTO));
            return ResponseEntity.ok().build();
        }
        catch (RecipeDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(path = "/recipe/save/{id}", produces = "application/json", consumes = "application/text")
    public ResponseEntity<?> saveRecipeToFilesystem(Long id, @RequestBody String filename)  {
        try {
            recipeService.saveRecipeToFilesystem(id, filename);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (RecipeDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = "/recipe/ratings/{id}", consumes = "application/json")
    public ResponseEntity<?> addRatingToRecipe(Long id, float rating) {
        try {
            recipeService.addRatingToRecipe(id, rating);
            return ResponseEntity.ok().build();
        }
        catch (RecipeDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
