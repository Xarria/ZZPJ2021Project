package com.zzpj.services.interfaces;

import com.zzpj.exceptions.IngredientNotFoundException;
import com.zzpj.exceptions.URLNotFoundException;
import com.zzpj.model.entities.Ingredient;

import java.io.IOException;
import java.util.List;

public interface IngredientServiceInterface {
    Ingredient getIngredientsByKeyword(String keyword) throws IngredientNotFoundException, IOException, URLNotFoundException;

    List<Ingredient> getAllIngredients();

    void addIngredient(Ingredient ingredient);

}
