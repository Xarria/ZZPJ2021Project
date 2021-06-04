package com.zzpj.model.mappers;

import com.zzpj.model.DTOs.IngredientDTO;
import com.zzpj.model.entities.Ingredient;

public class IngredientsMapper {

    public static IngredientDTO entityToDTO(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setCalories(ingredient.getCalories());
        ingredientDTO.setCarbohydrates(ingredient.getCarbohydrates());
        ingredientDTO.setProtein(ingredient.getProtein());
        ingredientDTO.setFats(ingredient.getFats());
        ingredientDTO.setQuantity(ingredient.getQuantity());
        return ingredientDTO;
    }

    public static Ingredient dtoToEntity(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDTO.getName());
        ingredient.setCalories(ingredientDTO.getCalories());
        ingredient.setCarbohydrates(ingredientDTO.getCarbohydrates());
        ingredient.setProtein(ingredientDTO.getProtein());
        ingredient.setFats(ingredientDTO.getFats());
        ingredient.setQuantity(ingredientDTO.getQuantity());
        return ingredient;
    }
}
