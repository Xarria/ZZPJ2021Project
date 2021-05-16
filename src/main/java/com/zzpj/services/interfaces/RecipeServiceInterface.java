package com.zzpj.services.interfaces;

import com.zzpj.DTOs.RecipeDTO;
import com.zzpj.model.Recipe;

public interface RecipeServiceInterface {

    void createRecipe(Recipe recipe);

    RecipeDTO getRecipeById(Long id);

    RecipeDTO getAllRecipes();

    void updateRecipe(Long id, Recipe updatedRecipe);

    void saveRecipeToFilesystem(Recipe recipe);

    void addRatingToRecipe(String name, float rating);
}
