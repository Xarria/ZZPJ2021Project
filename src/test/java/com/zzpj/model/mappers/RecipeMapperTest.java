package com.zzpj.model.mappers;


import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.Recipe;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeMapperTest {

    @Test
    void recipeDetailsDTOtoRecipeTest() {
        RecipeDetailsDTO recipeDTO = new RecipeDetailsDTO(1L, "Bułeczki", "Login", "Fajne bułeczki",
                new ArrayList<>(), 5F, 3, "vegan", null, 4,
                400, 45L, "EASY");

        assertDoesNotThrow(() -> RecipeMapper.detailsDTOtoEntity(recipeDTO));

        Recipe recipe = RecipeMapper.detailsDTOtoEntity(recipeDTO);

        assertEquals("Bułeczki", recipe.getName());
        assertEquals("Fajne bułeczki", recipe.getDescription());
        assertEquals(5F, recipe.getRating());
        assertEquals("Login", recipe.getAuthorLogin());
        assertEquals(3, recipe.getRatingsCount());
        assertEquals(4, recipe.getServings());
        assertEquals(400, recipe.getCalories());
        assertEquals(45L, recipe.getPrepareTimeInMinutes());
        assertEquals("EASY", recipe.getDifficulty());
    }

    @Test
    void entityToDetailsDTOTest() {
        Recipe recipe = new Recipe(1L, "Bułeczki", "Login", "Fajne bułeczki",
                new ArrayList<>(), 4F, 5, "vegan", null, 4,
                400, 45L, "EASY");

        assertDoesNotThrow(() -> RecipeMapper.entityToDetailsDTO(recipe));

        RecipeDetailsDTO recipeDTO = RecipeMapper.entityToDetailsDTO(recipe);

        assertEquals("Bułeczki", recipeDTO.getName());
        assertEquals("Login", recipeDTO.getAuthorLogin());
        assertEquals("Fajne bułeczki", recipeDTO.getDescription());
        assertEquals(4F, recipeDTO.getRating());
        assertEquals(5, recipeDTO.getRatingsCount());
        assertEquals(4, recipeDTO.getServings());
        assertEquals(400, recipeDTO.getCalories());
        assertEquals(45L, recipeDTO.getPreparationTimeInMinutes());
        assertEquals("EASY", recipeDTO.getDifficulty());
    }

    @Test
    void entityToGeneralDTO() {
        Recipe recipe = new Recipe(1L, "Bułeczki", "Login", "Fajne bułeczki",
                new ArrayList<>(), 4F, 5, "vegan", null, 4,
                400, 45L, "EASY");
        assertDoesNotThrow(() -> RecipeMapper.entityToGeneralDTO(recipe));

        RecipeGeneralDTO recipeDTO = RecipeMapper.entityToGeneralDTO(recipe);

        assertEquals("Bułeczki", recipeDTO.getName());
        assertEquals("Login", recipeDTO.getAuthorLogin());
        assertEquals(4F, recipeDTO.getRating());
        assertEquals(5, recipeDTO.getRatingsCount());
        assertEquals(400, recipeDTO.getCalories());
        assertEquals(45L, recipeDTO.getPreparationTimeInMinutes());
        assertEquals("EASY", recipeDTO.getDifficulty());
        assertEquals("Fajne bułeczki", recipeDTO.getDescription());
        assertEquals(4, recipeDTO.getServings());
        assertEquals("vegan", recipeDTO.getTags());
    }

    @Test
    void generalDTOToEntity() {
        RecipeGeneralDTO recipeDTO = new RecipeGeneralDTO(1L, "Bułeczki", "Login", "Mniam", 5F,
        5, "dairy,gluten", null, 4, 400, 40L, "EASY");

        assertDoesNotThrow(() -> RecipeMapper.generalDTOToEntity(recipeDTO));

        Recipe recipe = RecipeMapper.generalDTOToEntity(recipeDTO);

        assertEquals("Bułeczki", recipe.getName());
        assertEquals("Mniam", recipe.getDescription());
        assertEquals(5F, recipe.getRating());
        assertEquals("Login", recipe.getAuthorLogin());
        assertEquals(5, recipe.getRatingsCount());
        assertEquals("dairy,gluten", recipe.getRecipeTags());
        assertEquals(400, recipe.getCalories());
        assertEquals(4, recipe.getServings());
        assertEquals(40L, recipe.getPrepareTimeInMinutes());
        assertEquals("EASY", recipe.getDifficulty());
    }
}
