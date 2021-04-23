package com.zzpj.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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
    @Column(name = "name", nullable = false, updatable = true)
    private String name;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "author", nullable = false, updatable = false)
    private Account author;

    private String ingredients;

    private String description;

    private Float rating;

    private String tags;

    private Byte[] image;

    private Integer servings;

    private Integer calories;

    private Long expectedTime;

    private String difficulty;
}
