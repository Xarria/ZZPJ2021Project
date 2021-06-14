package com.zzpj.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Recipe")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "author", nullable = false, updatable = false)
    private String authorLogin;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> recipeIngredients;

    @NotNull
    @Column(name = "rating", nullable = false)
    private Float rating;

    @NotNull
    @Column(name = "ratings_count", nullable = false)
    private Integer ratingsCount;

    @Column(name = "tags", nullable = true)
    private String recipeTags;

    @Column(name = "image", nullable = true)
    private byte[] image;

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
