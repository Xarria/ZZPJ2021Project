package com.zzpj.repositories;

import com.zzpj.model.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findRecipeById(Long id);
}
