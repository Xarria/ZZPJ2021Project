package com.zzpj.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {

    // TODO nałożyć ograniczenia na pola
    private String name;

    private AccountAccessLevelDTO author;

    private String description;

    private List<IngredientDTO> ingredients = new ArrayList<>();

    private Float rating;

    private List<String> recipeTags = new ArrayList<>();

    private Byte[] image;

    private Integer servings;

    private Integer calories;

    private Long prepareTimeInMinutes;

    private String difficulty;
}
