package com.zzpj.services.interfaces;

import com.zzpj.exceptions.RecipeDoesNotExistException;
import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.Recipe;

import java.io.IOException;
import java.util.List;

public interface RecipeServiceInterface {

    void createRecipe(Recipe recipe);

    RecipeDetailsDTO getRecipeById(Long id) throws RecipeDoesNotExistException;

    List<RecipeGeneralDTO> getAllRecipes();

    void deleteRecipe(Long id) throws RecipeDoesNotExistException;

    void updateRecipe(Long id, Recipe updatedRecipe) throws RecipeDoesNotExistException;

    void saveRecipeToFilesystem(Long id, String filename) throws IOException, RecipeDoesNotExistException;

    void addRatingToRecipe(Long id, float rating) throws RecipeDoesNotExistException;
}
