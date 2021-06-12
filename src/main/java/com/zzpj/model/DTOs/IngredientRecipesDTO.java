package com.zzpj.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class IngredientRecipesDTO {

    @NotNull
    private String name;

    @NotNull
    private Double quantity;

    @Positive
    private Double calories;

    @PositiveOrZero
    private Double protein;

    @PositiveOrZero
    private Double carbohydrates;

    @PositiveOrZero
    private Double fats;
}
