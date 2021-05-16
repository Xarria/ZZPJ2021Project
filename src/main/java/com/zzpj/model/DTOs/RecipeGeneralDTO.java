package com.zzpj.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeGeneralDTO {

    // TODO nałożyć ograniczenia na pola
    private String name;

    private String authorLogin;

    private Float rating;

    private Integer calories;

    private Long preparationTimeInMinutes;

    private Byte[] image;
}
