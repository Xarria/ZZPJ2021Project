package com.zzpj.services;

import com.zzpj.DTOs.RecipeDTO;
import com.zzpj.model.Recipe;
import com.zzpj.repository.AccountRepository;
import com.zzpj.repository.RecipeRepository;
import com.zzpj.services.interfaces.RecipeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeService implements RecipeServiceInterface {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public void createRecipe(Recipe recipe) {

    }

    @Override
    public RecipeDTO getRecipeById(Long id) {
        return null;
    }

    @Override
    public RecipeDTO getAllRecipes() {
        return null;
    }

    @Override
    public void updateRecipe(Long id, Recipe updatedRecipe) {

    }

    @Override
    public void saveRecipeToFilesystem(Recipe recipe) {

    }

    @Override
    public void addRatingToRecipe(String name, float rating) {

    }
}
