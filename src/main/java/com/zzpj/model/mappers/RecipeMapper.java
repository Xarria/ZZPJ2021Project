package com.zzpj.model.mappers;

import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.Recipe;

import java.util.stream.Collectors;

public class RecipeMapper {

    public static RecipeGeneralDTO entityToGeneralDTO(Recipe recipe) {
        RecipeGeneralDTO recipeGeneralDTO = new RecipeGeneralDTO();

        recipeGeneralDTO.setId(recipe.getId());
        recipeGeneralDTO.setName(recipe.getName());
        recipeGeneralDTO.setAuthorLogin(recipe.getAuthorLogin());
        recipeGeneralDTO.setRating(recipe.getRating());
        recipeGeneralDTO.setRatingsCount(recipe.getRatingsCount());
        recipeGeneralDTO.setCalories(recipe.getCalories());
        recipeGeneralDTO.setPreparationTimeInMinutes(recipe.getPrepareTimeInMinutes());
        recipeGeneralDTO.setDifficulty(recipe.getDifficulty());
        recipeGeneralDTO.setImage(recipe.getImage());

        return recipeGeneralDTO;
    }

    public static RecipeDetailsDTO entityToDetailsDTO(Recipe recipe) {
        RecipeDetailsDTO recipeDetailsDTO = new RecipeDetailsDTO();

        recipeDetailsDTO.setId(recipe.getId());
        recipeDetailsDTO.setName(recipe.getName());
        recipeDetailsDTO.setAuthorLogin(recipe.getAuthorLogin());
        recipeDetailsDTO.setDescription(recipe.getDescription());
        recipeDetailsDTO.setIngredients(recipe.getRecipeIngredients().stream()
                .map(IngredientsMapper::entityToDTO)
                .collect(Collectors.toList()));
        recipeDetailsDTO.setRating(recipe.getRating());
        recipeDetailsDTO.setRatingsCount(recipe.getRatingsCount());
        recipeDetailsDTO.setTags(recipe.getRecipeTags());
        recipeDetailsDTO.setImage(recipe.getImage());
        recipeDetailsDTO.setServings(recipe.getServings());
        recipeDetailsDTO.setCalories(recipe.getCalories());
        recipeDetailsDTO.setPreparationTimeInMinutes(recipe.getPrepareTimeInMinutes());
        recipeDetailsDTO.setDifficulty(recipe.getDifficulty());

        return recipeDetailsDTO;
    }

    public static Recipe generalDTOToEntity(RecipeGeneralDTO recipeGeneralDTO) {
        Recipe recipe = new Recipe();

        if (recipeGeneralDTO.getId() != null) {
            recipe.setId(recipeGeneralDTO.getId());
        }
        recipe.setName(recipeGeneralDTO.getName());
        recipe.setAuthorLogin(recipeGeneralDTO.getAuthorLogin());
        recipe.setRating(recipeGeneralDTO.getRating());
        recipe.setRatingsCount(recipeGeneralDTO.getRatingsCount());
        recipe.setCalories(recipeGeneralDTO.getCalories());
        recipe.setPrepareTimeInMinutes(recipeGeneralDTO.getPreparationTimeInMinutes());
        recipe.setDifficulty(recipeGeneralDTO.getDifficulty());
        recipe.setImage(recipeGeneralDTO.getImage());

        return recipe;
    }

    public static Recipe detailsDTOtoEntity(RecipeDetailsDTO recipeDetailsDTO) {
        Recipe recipe = new Recipe();

        if (recipeDetailsDTO.getId() != null) {
            recipe.setId(recipeDetailsDTO.getId());
        }
        recipe.setName(recipeDetailsDTO.getName());
        recipe.setAuthorLogin(recipeDetailsDTO.getAuthorLogin());
        recipe.setDescription(recipeDetailsDTO.getDescription());
        recipe.setRecipeIngredients(recipeDetailsDTO.getIngredients().stream()
                .map(IngredientsMapper::dtoToEntity)
                .collect(Collectors.toList()));
        recipe.setRating(recipeDetailsDTO.getRating());
        recipe.setRatingsCount(recipeDetailsDTO.getRatingsCount());
        recipe.setRecipeTags(recipeDetailsDTO.getTags());
        recipe.setImage(recipeDetailsDTO.getImage());
        recipe.setServings(recipeDetailsDTO.getServings());
        recipe.setCalories(recipeDetailsDTO.getCalories());
        recipe.setPrepareTimeInMinutes(recipeDetailsDTO.getPreparationTimeInMinutes());
        recipe.setDifficulty(recipeDetailsDTO.getDifficulty());

        return recipe;
    }

}
