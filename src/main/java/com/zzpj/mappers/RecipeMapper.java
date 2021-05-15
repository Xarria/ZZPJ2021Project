package com.zzpj.mappers;

import com.zzpj.DTOs.RecipeDTO;
import com.zzpj.model.Recipe;

import java.util.stream.Collectors;

public class RecipeMapper {

    public static RecipeDTO entityToDTO(Recipe recipe) {
        // TODO Weryfikacja osoby robiącej na Recipe
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setName(recipe.getName());
        recipeDTO.setAuthor(AccountMapper.entityToDTO(recipe.getAuthor()));
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setIngredients(recipe.getRecipeIngredients().stream()
                .map(IngredientsMapper::entityToDTO)
                .collect(Collectors.toList()));
        recipeDTO.setRating(recipe.getRating());
        recipeDTO.setRecipeTags(recipe.getRecipeTags());
        recipeDTO.setImage(recipe.getImage());
        recipeDTO.setServings(recipe.getServings());
        recipeDTO.setCalories(recipe.getCalories());
        recipeDTO.setPrepareTimeInMinutes(recipe.getPrepareTimeInMinutes());
        recipeDTO.setDifficulty(recipe.getDifficulty());

        return recipeDTO;
    }
}
