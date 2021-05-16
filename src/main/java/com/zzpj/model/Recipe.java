package com.zzpj.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Recipe")
public class Recipe {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "author", nullable = false, updatable = false)
    private Account author;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "ingredients", nullable = false)
    @ManyToMany
    @JoinTable(name = "Recipe_Ingredient")
    private List<Ingredient> recipeIngredients;

    @NotNull
    @Column(name = "rating", nullable = false)
    private Float rating;

    @ElementCollection
    private List<String> recipeTags = new ArrayList<>();

    @NotNull
    @Column(name = "image", nullable = false)
    private Byte[] image;

    @NotNull
    @Column(name = "servings", nullable = false)
    private Integer servings;

    @Column(name = "calories")
    private Integer calories;

    @NotNull
    @Column(name = "prepare_time_in_minutes", nullable = false)
    private Long prepareTimeInMinutes;

    @Column(name = "difficulty")
    private String difficulty;
}
