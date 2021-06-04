package com.zzpj.model.DTOs;

import com.zzpj.model.entities.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class IngredientRecipesDTO {

    @NotNull
    private String name;

    @NotNull
    private Long quantity;

    @Positive
    private int calories;

    @PositiveOrZero
    private int protein;

    @PositiveOrZero
    private int carbohydrates;

    @PositiveOrZero
    private int fats;

    List<Recipe> recipes = new ArrayList<>();
}
