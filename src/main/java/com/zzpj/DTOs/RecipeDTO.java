package com.zzpj.DTOs;

import com.zzpj.model.DTOs.AccountNoRecipesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {


    @NotBlank
    private String name;

    @NotNull
    private AccountNoRecipesDTO author;

    @NotBlank
    private String description;

    @NotNull
    private List<IngredientDTO> recipeIngredients;

    @NotNull
    @Range(min = 1, max = 5)
    private Float rating;

    private List<String> recipeTags = new ArrayList<>();

    private List<AccountNoRecipesDTO> accounts = new ArrayList<>();

    @NotNull
    private Byte[] image;

    @NotNull
    @Min(1)
    private Integer servings;

    @Positive
    private Integer calories;

    @NotNull
    @Positive
    private Long prepareTimeInMinutes;

    @NotBlank
    private String difficulty;
}
