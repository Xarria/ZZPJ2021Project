package com.zzpj.model.mappers;

import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.Recipe;

import java.util.stream.Collectors;

public class RecipeMapper {

    public static RecipeGeneralDTO entityToGeneralDTO(Recipe recipe) {
        // TODO Weryfikacja osoby robiącej Recipe dalej
        RecipeGeneralDTO recipeGeneralDTO = new RecipeGeneralDTO();

        recipeGeneralDTO.setName(recipe.getName());
        recipeGeneralDTO.setAuthorLogin(recipe.getAuthor().getLogin());
        recipeGeneralDTO.setRating(recipe.getRating());
        recipeGeneralDTO.setCalories(recipe.getCalories());
        recipeGeneralDTO.setPreparationTimeInMinutes(recipe.getPrepareTimeInMinutes());
        recipeGeneralDTO.setImage(recipe.getImage());

        return recipeGeneralDTO;
    }

    public static RecipeDetailsDTO entityToDetailsDTO(Recipe recipe) {
        // TODO Weryfikacja osoby robiącej Recipe dalej
        RecipeDetailsDTO recipeDetailsDTO = new RecipeDetailsDTO();

        recipeDetailsDTO.setName(recipe.getName());
        recipeDetailsDTO.setAuthorLogin(recipe.getAuthor().getLogin());
        recipeDetailsDTO.setDescription(recipe.getDescription());
        recipeDetailsDTO.setIngredients(recipe.getRecipeIngredients().stream()
                .map(IngredientsMapper::entityToDTO)
                .collect(Collectors.toList()));
        recipeDetailsDTO.setRating(recipe.getRating());
        recipeDetailsDTO.setTags(recipe.getRecipeTags());
        recipeDetailsDTO.setImage(recipe.getImage());
        recipeDetailsDTO.setServings(recipe.getServings());
        recipeDetailsDTO.setCalories(recipe.getCalories());
        recipeDetailsDTO.setPreparationTimeInMinutes(recipe.getPrepareTimeInMinutes());
        recipeDetailsDTO.setDifficulty(recipe.getDifficulty());

        return new RecipeDetailsDTO();
    }
}
