package com.zzpj.mappers;

import com.zzpj.DTOs.AccountAccessLevelDTO;
import com.zzpj.DTOs.RecipeDTO;
import com.zzpj.model.Account;
import com.zzpj.model.Recipe;

import java.util.ArrayList;

public class RecipeMapper {

    public static Recipe recipeDTOtoRecipe(RecipeDTO recipeDTO){
        Recipe recipe = new Recipe();
        if(recipeDTO != null){
            //TODO Ustawianie kont, składników i autora po zmapowaniu na Account i Ingredient
            recipe.setAccounts(new ArrayList<>());
            recipe.setAuthor(new Account());
            recipe.setName(recipeDTO.getName());
            recipe.setDescription(recipeDTO.getDescription());
            recipe.setRecipeIngredients(new ArrayList<>());
            recipe.setRecipeTags(recipeDTO.getRecipeTags());
            recipe.setCalories(recipeDTO.getCalories());
            recipe.setDifficulty(recipeDTO.getDifficulty().toString());
            recipe.setImage(recipeDTO.getImage());
            recipe.setPrepareTimeInMinutes(recipeDTO.getPrepareTimeInMinutes());
            recipe.setRating(recipeDTO.getRating());
            recipe.setServings(recipeDTO.getServings());
        }
        return recipe;
    }

    public static RecipeDTO recipeToRecipeDTO(Recipe recipe){
        RecipeDTO recipeDTO = new RecipeDTO();
        if(recipe != null){
            //TODO Ustawianie kont, składników i autora po zmapowaniu na Account i Ingredient
            recipeDTO.setAccounts(new ArrayList<>());
            recipeDTO.setAuthor(new AccountAccessLevelDTO());
            recipeDTO.setName(recipe.getName());
            recipeDTO.setDescription(recipe.getDescription());
            recipeDTO.setRecipeIngredients(new ArrayList<>());
            recipeDTO.setRecipeTags(recipe.getRecipeTags());
            recipeDTO.setCalories(recipe.getCalories());
            recipeDTO.setDifficulty(recipe.getDifficulty());
            recipeDTO.setImage(recipe.getImage());
            recipeDTO.setPrepareTimeInMinutes(recipe.getPrepareTimeInMinutes());
            recipeDTO.setRating(recipe.getRating());
            recipeDTO.setServings(recipe.getServings());
        }
        return recipeDTO;
    }
}
