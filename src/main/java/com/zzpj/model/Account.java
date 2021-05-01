package com.zzpj.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "Account")
public class Account {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "login", nullable = false)
    private String login; // max 24 chars

    @NotNull
    @ToString.Exclude
    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "favorite_recipes")
    @OneToMany
    private List<Recipe> favoriteRecipes;

    @NotNull
    @Column(name = "access_level", nullable = false)
    @ManyToOne
    private AccessLevel accessLevel;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;
}
