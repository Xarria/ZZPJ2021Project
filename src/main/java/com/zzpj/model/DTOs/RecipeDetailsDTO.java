package com.zzpj.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetailsDTO {

    // TODO nałożyć ograniczenia na pola
    private String name;

    private String authorLogin;

    private String description;

    private List<IngredientDTO> ingredients = new ArrayList<>();

    private Float rating;

    private List<String> tags = new ArrayList<>();

    private Byte[] image;

    private Integer servings;

    private Integer calories;

    private Long preparationTimeInMinutes;

    private String difficulty;
}
