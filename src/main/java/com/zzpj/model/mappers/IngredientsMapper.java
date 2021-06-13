package com.zzpj.model.mappers;

import com.zzpj.model.DTOs.IngredientDTO;
import com.zzpj.model.entities.Ingredient;
import org.apache.commons.math3.util.Precision;

import java.text.DecimalFormat;

public class IngredientsMapper {

    private static final DecimalFormat df = new DecimalFormat("##.00");

    public static IngredientDTO entityToDTO(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setCalories(ingredient.getCalories());
        ingredientDTO.setCarbohydrates(Precision.round(ingredient.getCarbohydrates(), 2));
        ingredientDTO.setProtein(Precision.round(ingredient.getProtein(), 2));
        ingredientDTO.setFats(Precision.round(ingredient.getFats(), 2));
        ingredientDTO.setQuantity(ingredient.getQuantity());
        return ingredientDTO;
    }

    public static Ingredient dtoToEntity(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient.setCalories(ingredientDTO.getCalories());
        ingredient.setCarbohydrates(Precision.round(ingredientDTO.getCarbohydrates(), 2));
        ingredient.setProtein(Precision.round(ingredientDTO.getProtein(), 2));
        ingredient.setFats(Precision.round(ingredientDTO.getFats(), 2));
        ingredient.setQuantity(ingredientDTO.getQuantity());
        return ingredient;
    }

    public static Ingredient dtoToEntity(IngredientDTO ingredientDTO, Double quantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient.setCalories(ingredientDTO.getCalories());
        ingredient.setCarbohydrates(ingredientDTO.getCarbohydrates());
        ingredient.setProtein(ingredientDTO.getProtein());
        ingredient.setFats(ingredientDTO.getFats());
        ingredient.setQuantity(quantity);
        return ingredient;
    }
}
