package com.zzpj.services;

import com.zzpj.exceptions.IngredientNotFoundException;
import com.zzpj.exceptions.URLNotFoundException;
import com.zzpj.model.entities.Ingredient;
import com.zzpj.repositories.IngredientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private IngredientService ingredientService;
    @Mock
    Ingredient ingredient = new Ingredient();
    @Spy
    private final List<Ingredient> ingredients = new ArrayList<>();

    private final String randomKeyword = "mint";

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(ingredient.getName()).thenReturn("Mint");
        Mockito.when(ingredient.getQuantity()).thenReturn(1.0);
        Mockito.when(ingredient.getCalories()).thenReturn(44.0);
        Mockito.when(ingredient.getProtein()).thenReturn(3.29);
        Mockito.when(ingredient.getCarbohydrates()).thenReturn(8.41);
        Mockito.when(ingredient.getFats()).thenReturn(0.73);
        ingredients.add(new Ingredient());
        ingredients.add(new Ingredient());
        ingredients.add(new Ingredient());
        Mockito.when(ingredientRepository.findAll()).thenReturn(ingredients);
    }

    @Test
    void getIngredientsByKeyword() throws URLNotFoundException, IOException, IngredientNotFoundException {
        Ingredient i = ingredientService.getIngredientsByKeyword(randomKeyword);
        Assertions.assertEquals(i.getName(), ingredient.getName());
        Assertions.assertEquals(i.getQuantity(), ingredient.getQuantity());
        Assertions.assertEquals(i.getCalories(), ingredient.getCalories(), 0.001);
        Assertions.assertEquals(i.getProtein(), ingredient.getProtein(), 0.001);
        Assertions.assertEquals(i.getCarbohydrates(), ingredient.getCarbohydrates(), 0.001);
        Assertions.assertEquals(i.getFats(), ingredient.getFats(), 0.001);
    }

    @Test
    void getRequest() {
        Assertions.assertEquals("https://api.edamam.com/api/food-database/v2/parser?app_id=2b779c24&app_key=30e0fde1bdd3f9da8c0c067886853494&ingr=mint&categoryLabel=food", ingredientService.getRequest(randomKeyword));
    }

    @Test
    void replaceSpacesInKeyword() {
        String stringWithSpaces = "A B C D E F GTR";
        Assertions.assertEquals("A%20B%20C%20D%20E%20F%20GTR", ingredientService.replaceSpacesInKeyword(stringWithSpaces));
    }

    @Test
    void getAllIngredients() {
        Assertions.assertEquals(3, ingredientService.getAllIngredients().size());
    }
}
