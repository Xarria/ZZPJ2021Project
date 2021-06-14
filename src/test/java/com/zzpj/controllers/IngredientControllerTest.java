package com.zzpj.controllers;

import com.zzpj.exceptions.IngredientNotFoundException;
import com.zzpj.exceptions.URLNotFoundException;
import com.zzpj.model.DTOs.IngredientDTO;
import com.zzpj.model.entities.Ingredient;
import com.zzpj.model.mappers.IngredientsMapper;
import com.zzpj.services.interfaces.IngredientServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class IngredientControllerTest {

    private final String keyword = "mint";
    private final List<Ingredient> ingredients = new ArrayList<>();

    @Mock
    private IngredientServiceInterface ingredientService;

    @InjectMocks
    private IngredientController ingredientController;

    @Spy
    private final Ingredient ingredient = new Ingredient();
    @Spy
    private final Ingredient oneOfManyIngredients = new Ingredient();

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(ingredient.getName()).thenReturn("Mint");
        Mockito.when(ingredient.getQuantity()).thenReturn(1.0);
        Mockito.when(ingredient.getCalories()).thenReturn(44.0);
        Mockito.when(ingredient.getProtein()).thenReturn(3.29);
        Mockito.when(ingredient.getCarbohydrates()).thenReturn(8.41);
        Mockito.when(ingredient.getFats()).thenReturn(0.73);
        oneOfManyIngredients.setName("Whatever");
        oneOfManyIngredients.setQuantity(1.0);
        oneOfManyIngredients.setCalories(1.0);
        oneOfManyIngredients.setProtein(1.0);
        oneOfManyIngredients.setCarbohydrates(1.0);
        oneOfManyIngredients.setFats(1.0);
        ingredients.add(oneOfManyIngredients);
    }

    @Test
    void getIngredient() throws URLNotFoundException, IOException, IngredientNotFoundException {
        when(ingredientService.getIngredientsByKeyword("mint")).thenReturn(ingredient);
        assertDoesNotThrow(() -> ingredientController.getIngredient(keyword));
        assertEquals(IngredientsMapper.entityToDTO(ingredient), ingredientController.getIngredient(keyword).getBody());
    }

    @Test
    void getAllIngredients() {
        when(ingredientService.getAllIngredients()).thenReturn(ingredients);
        assertDoesNotThrow(() -> ingredientController.getAllIngredients());
        List<IngredientDTO> list = ingredients.stream()
            .map(IngredientsMapper::entityToDTO).collect(Collectors.toList());
        assertEquals(list, ingredientController.getAllIngredients().getBody());
    }
}