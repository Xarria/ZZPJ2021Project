package com.zzpj.services.interfaces;

import com.zzpj.exceptions.IngredientNotFoundException;
import com.zzpj.exceptions.NotAnAuthorException;
import com.zzpj.exceptions.RecipeDoesNotExistException;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.Ingredient;
import com.zzpj.model.entities.Recipe;

import java.io.IOException;
import java.util.List;

public interface RecipeServiceInterface {

    void createRecipe(Recipe recipe);

    Recipe getRecipeById(Long id) throws RecipeDoesNotExistException;

    List<Recipe> getAllRecipes();

    void deleteRecipe(Long id) throws RecipeDoesNotExistException;

    void updateRecipe(Long id, Recipe updatedRecipe, String authorLogin) throws RecipeDoesNotExistException, NotAnAuthorException;

    void addIngredient(Long recipeId, Ingredient ingredient, String authorLogin) throws RecipeDoesNotExistException, NotAnAuthorException;

    String sendRecipeByMail(Long id) throws IOException, RecipeDoesNotExistException;

    void addRatingToRecipe(Long id, float rating) throws RecipeDoesNotExistException;

    String getShoppingList(List<Long> recipes);

    List<Recipe> getAllRecipesForAccount(String login);

    List<Recipe> getFavouriteRecipesForAccount(String login);

    List<Recipe> getRecommendationBasedOnLikings(Account account, List<String> unwantedTags);

    void removeIngredientFromRecipe(Long id, String ingredientName, String name) throws RecipeDoesNotExistException, NotAnAuthorException, IngredientNotFoundException;

    void addRecipeToFavourites(String login, Long id);
}
