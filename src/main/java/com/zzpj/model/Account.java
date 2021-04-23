package com.zzpj.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private String login;

    private String password;

    private String email;

    private String favouriteRecipes;

    private String accessLevel;

    private Boolean active;
}
