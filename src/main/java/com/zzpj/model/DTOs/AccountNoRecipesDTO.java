package com.zzpj.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountNoRecipesDTO {

    @NotBlank
    @Size(max = 24, message = "Login can not have more than 24 characters")
    private String login;

    @Size(min = 8, message = "Password must not have less than 8 characters")
    private String password;

    @Email
    @NotBlank
    @Size(max = 100, message = "Email can not have more than 100 characters")
    private String email;

    @NotNull
    private String accessLevel;

    private Boolean active;
}
