package com.zzpj.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "RecipeTagCon")
public class RecipeTagCon {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "recipe_id", nullable = false, updatable = false)
    private Long idRecipe;

    @NotNull
    @Column(name = "tag_id", nullable = false, updatable = false)
    private Long idTag;
}
