package com.zzpj.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
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
    @ManyToMany
    @JoinTable(name = "Recipe_Ingredient")
    private List<Ingredient> recipeIngredients;

    @NotNull
    @Column(name = "rating", nullable = false)
    private Float rating;

    @NotNull
    @Column(name = "ratings_count", nullable = false)
    private Integer ratingsCount;

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
