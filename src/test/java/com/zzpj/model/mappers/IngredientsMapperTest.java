package com.zzpj.model.mappers;

import com.zzpj.model.DTOs.IngredientDTO;
import com.zzpj.model.entities.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientsMapperTest {

    private final Long id = 1L;
    private final String name = "flour";
    private final Double quantity = 100.0;
    private final Double customQuantity = 200.0;
    private final Double calories = 364.0;
    private final Double protein = 10.33;
    private final Double carbohydrates = 76.31;
    private final Double fats = 0.98;
    private Ingredient ingredient;
    private IngredientDTO ingredientDTO;

    @BeforeEach
    void init() {
        ingredient = createIngredient();
        ingredientDTO = createIngredientDTO();
    }

    @Test
    void entityToDTO() {
        IngredientDTO testIngredientDTO = IngredientsMapper.entityToDTO(ingredient);

        assertAll(
                () -> assertEquals(name, testIngredientDTO.getName()),
                () -> assertEquals(quantity, testIngredientDTO.getQuantity()),
                () -> assertEquals(calories, testIngredientDTO.getCalories()),
                () -> assertEquals(protein, testIngredientDTO.getProtein()),
                () -> assertEquals(carbohydrates, testIngredientDTO.getCarbohydrates()),
                () -> assertEquals(fats, testIngredientDTO.getFats())
        );
    }

    @Test
    void dtoToEntity() {
        Ingredient testIngredient = IngredientsMapper.dtoToEntity(ingredientDTO);

        assertAll(
                () -> assertEquals(name, testIngredient.getName()),
                () -> assertEquals(quantity, testIngredient.getQuantity()),
                () -> assertEquals(calories, testIngredient.getCalories()),
                () -> assertEquals(protein, testIngredient.getProtein()),
                () -> assertEquals(carbohydrates, testIngredient.getCarbohydrates()),
                () -> assertEquals(fats, testIngredient.getFats())
        );
    }

    @Test
    void dtoToEntityWithQuantity() {
        Ingredient testIngredient = IngredientsMapper.dtoToEntity(ingredientDTO, customQuantity);

        assertAll(
                () -> assertEquals(name, testIngredient.getName()),
                () -> assertEquals(customQuantity, testIngredient.getQuantity()),
                () -> assertEquals(calories, testIngredient.getCalories()),
                () -> assertEquals(protein, testIngredient.getProtein()),
                () -> assertEquals(carbohydrates, testIngredient.getCarbohydrates()),
                () -> assertEquals(fats, testIngredient.getFats())
        );
    }

    private Ingredient createIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setName(name);
        ingredient.setQuantity(quantity);
        ingredient.setCalories(calories);
        ingredient.setProtein(protein);
        ingredient.setCarbohydrates(carbohydrates);
        ingredient.setFats(fats);
        return ingredient;
    }

    private IngredientDTO createIngredientDTO() {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName(name);
        ingredientDTO.setQuantity(quantity);
        ingredientDTO.setCalories(calories);
        ingredientDTO.setProtein(protein);
        ingredientDTO.setCarbohydrates(carbohydrates);
        ingredientDTO.setFats(fats);
        return ingredientDTO;
    }
}
