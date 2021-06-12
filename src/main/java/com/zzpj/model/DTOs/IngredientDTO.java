package com.zzpj.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

    @NotNull
    private String name;

    @NotNull
    private Double quantity;

    @PositiveOrZero
    private Double calories;

    @PositiveOrZero
    private Double protein;

    @PositiveOrZero
    private Double carbohydrates;

    @PositiveOrZero
    private Double fats;
}
