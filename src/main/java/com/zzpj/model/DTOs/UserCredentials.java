package com.zzpj.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {

    @NotBlank
    private String username;

    @NotBlank
    @ToString.Exclude
    private String password;
}
