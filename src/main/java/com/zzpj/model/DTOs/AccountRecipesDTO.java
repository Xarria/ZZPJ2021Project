package com.zzpj.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRecipesDTO {

    @NotBlank
    @Size(max = 24, message = "Login can not have more than 24 characters")
    private String login;

    @Email
    @NotBlank
    @Size(max = 100, message = "Email can not have more than 100 characters")
    private String email;

    private List<RecipeGeneralDTO> favouriteRecipes = new ArrayList<>();
}
