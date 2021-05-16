package com.zzpj.mappers;

import com.zzpj.DTOs.AccountAccessLevelDTO;
import com.zzpj.DTOs.RecipeDTO;
import com.zzpj.model.Account;
import com.zzpj.model.Recipe;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RecipeMapperTest {

    @Test
    void recipeDTOtoRecipe() {
        RecipeDTO recipeDTO = new RecipeDTO("Bułeczki", new AccountAccessLevelDTO(), "Fajne bułeczki",
                new ArrayList<>(), 5F, new ArrayList<>(), new ArrayList<>(), null, 4,
                400, 45L, "EASY");

        assertDoesNotThrow(() -> RecipeMapper.recipeDTOtoRecipe(recipeDTO));

        Recipe recipe = RecipeMapper.recipeDTOtoRecipe(recipeDTO);

        assertEquals("Bułeczki", recipe.getName());
        assertEquals("Fajne bułeczki", recipe.getDescription());
        assertEquals(5F, recipe.getRating());
        assertEquals(4, recipe.getServings());
        assertEquals(400, recipe.getCalories());
        assertEquals(45L, recipe.getPrepareTimeInMinutes());
        assertEquals("EASY", recipe.getDifficulty());
    }

    @Test
    void recipeToRecipeDTO() {
        Recipe recipe = new Recipe(1L, "Bułeczki", new Account(), "Fajne bułeczki",
                new ArrayList<>(), 5F, new ArrayList<>(), new ArrayList<>(), null, 4,
                400, 45L, "EASY");

        assertDoesNotThrow(() -> RecipeMapper.recipeToRecipeDTO(recipe));

        RecipeDTO recipeDTO = RecipeMapper.recipeToRecipeDTO(recipe);

        assertEquals("Bułeczki", recipeDTO.getName());
        assertEquals("Fajne bułeczki", recipeDTO.getDescription());
        assertEquals(5F, recipeDTO.getRating());
        assertEquals(4, recipeDTO.getServings());
        assertEquals(400, recipeDTO.getCalories());
        assertEquals(45L, recipeDTO.getPrepareTimeInMinutes());
        assertEquals("EASY", recipeDTO.getDifficulty());
    }
}
