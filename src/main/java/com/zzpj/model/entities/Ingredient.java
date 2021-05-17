package com.zzpj.model.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Ingredient")
public class Ingredient {

    @NotNull
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "calories")
    private int calories;

    @Column(name = "protein")
    private int protein;

    @Column(name = "carbohydrates")
    private int carbohydrates;

    @Column(name = "fats")
    private int fats;

    @ManyToMany(mappedBy = "recipeIngredients")
    List<Recipe> recipes = new ArrayList<>();
}
