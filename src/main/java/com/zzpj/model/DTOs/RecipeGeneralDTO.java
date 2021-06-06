package com.zzpj.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeGeneralDTO {

    @NotBlank
    private String name;

    @NotNull
    private AccountNoRecipesDTO author;

    @NotNull
    @Range(min = 1, max = 5)
    private Float rating;

    private int ratingsCount;

    @Positive
    private Integer calories;

    @NotNull
    @Positive
    private Long preparationTimeInMinutes;

    private Byte[] image;
}
