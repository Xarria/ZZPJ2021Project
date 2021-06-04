package com.zzpj.mappers;


import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.Recipe;
import com.zzpj.model.mappers.RecipeMapper;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RecipeMapperTest {

//    @Test
//    void recipeDTOtoRecipeTest() {
//        RecipeDetailsDTO recipeDTO = new RecipeDetailsDTO("Bułeczki", "user1", "Fajne bułeczki",
//                new ArrayList<>(), 5F, new ArrayList<>(), null, 4,
//                400, 45L, "EASY");
//
//        assertDoesNotThrow(() -> RecipeMapper.recipeDTOtoRecipe(recipeDTO));
//
//        Recipe recipe = RecipeMapper.recipeDTOtoRecipe(recipeDTO);
//
//        assertEquals("Bułeczki", recipe.getName());
//        assertEquals("Fajne bułeczki", recipe.getDescription());
//        assertEquals(5F, recipe.getRating());
//        assertEquals(4, recipe.getServings());
//        assertEquals(400, recipe.getCalories());
//        assertEquals(45L, recipe.getPrepareTimeInMinutes());
//        assertEquals("EASY", recipe.getDifficulty());
//    }

    @Test
    void entityToDetailsDTOTest() {
        Recipe recipe = new Recipe(1L, "Bułeczki", new Account(), "Fajne bułeczki",
                new ArrayList<>(), 5F, 5, new ArrayList<>(), null, 4,
                400, 45L, "EASY");

        assertDoesNotThrow(() -> RecipeMapper.entityToDetailsDTO(recipe));

        RecipeDetailsDTO recipeDTO = RecipeMapper.entityToDetailsDTO(recipe);

        assertEquals("Bułeczki", recipeDTO.getName());
        assertEquals("Fajne bułeczki", recipeDTO.getDescription());
        assertEquals(5F, recipeDTO.getRating());
        assertEquals(4, recipeDTO.getServings());
        assertEquals(400, recipeDTO.getCalories());
        assertEquals(45L, recipeDTO.getPreparationTimeInMinutes());
        assertEquals("EASY", recipeDTO.getDifficulty());
    }
}
