package com.zzpj.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeGeneralDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private String authorLogin;

    @NotBlank
    private String description;

    @NotNull
    @Range(min = 1, max = 5)
    private Float rating;

    private int ratingsCount;

    private String tags;

    private byte[] image;

    @NotNull
    @Min(1)
    private Integer servings;

    @Positive
    private Integer calories;

    @NotNull
    @Positive
    private Long preparationTimeInMinutes;

    @NotBlank
    private String difficulty;

}
