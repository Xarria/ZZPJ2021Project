package com.zzpj.model.DTOs;

import com.zzpj.model.entities.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

    @NotNull
    private String name;

    @NotNull
    private Double quantity;

    @Positive
    private int calories;

    @PositiveOrZero
    private int protein;

    @PositiveOrZero
    private int carbohydrates;

    @PositiveOrZero
    private int fats;
}
