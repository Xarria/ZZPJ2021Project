package com.zzpj.services.interfaces;

import com.zzpj.exceptions.RecipeDoesNotExistException;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.Ingredient;
import com.zzpj.model.entities.Recipe;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface RecipeServiceInterface {

    void createRecipe(Recipe recipe);

    Recipe getRecipeById(Long id) throws RecipeDoesNotExistException;

    List<Recipe> getAllRecipes();

    void deleteRecipe(Long id) throws RecipeDoesNotExistException;

    void updateRecipe(Long id, Recipe updatedRecipe) throws RecipeDoesNotExistException;

    void addIngredient(Long recipeId, Ingredient ingredient) throws RecipeDoesNotExistException;

    String sendRecipeByMail(Long id) throws IOException, RecipeDoesNotExistException;

    void addRatingToRecipe(Long id, float rating) throws RecipeDoesNotExistException;

    String getShoppingList(List<Long> recipes);

    List<Recipe> getAllRecipesForAccount(String login);

    List<Recipe> getFavouriteRecipesForAccount(String login);

    List<Recipe> getRecommendationBasedOnLikings(Account account, List<String> unwantedTags);
}
