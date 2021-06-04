package com.zzpj.model.mappers;

import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.AccessLevel;
import com.zzpj.model.entities.Recipe;

import java.util.stream.Collectors;

public class RecipeMapper {

    public static RecipeGeneralDTO entityToGeneralDTO(Recipe recipe) {
        RecipeGeneralDTO recipeGeneralDTO = new RecipeGeneralDTO();

        recipeGeneralDTO.setName(recipe.getName());
        recipeGeneralDTO.setAuthor(AccountMapper.entityToAdminDTO(recipe.getAuthor()));
        recipeGeneralDTO.setRating(recipe.getRating());
        recipeGeneralDTO.setRatingsCount(recipe.getRatingsCount());
        recipeGeneralDTO.setCalories(recipe.getCalories());
        recipeGeneralDTO.setPreparationTimeInMinutes(recipe.getPrepareTimeInMinutes());
        recipeGeneralDTO.setImage(recipe.getImage());

        return recipeGeneralDTO;
    }

    public static RecipeDetailsDTO entityToDetailsDTO(Recipe recipe) {
        RecipeDetailsDTO recipeDetailsDTO = new RecipeDetailsDTO();

        recipeDetailsDTO.setName(recipe.getName());
        recipeDetailsDTO.setAuthor(AccountMapper.entityToAdminDTO(recipe.getAuthor()));
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

    public static Recipe detailsDTOtoEntity(RecipeDetailsDTO recipeDetailsDTO, AccessLevel accessLevel) {
        Recipe recipe = new Recipe();

        recipe.setName(recipeDetailsDTO.getName());
        recipe.setAuthor(AccountMapper.noRecipesDTOWithAccessLevelToEntity(recipeDetailsDTO.getAuthor(), accessLevel));
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
