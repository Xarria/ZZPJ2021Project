package com.zzpj.services.interfaces;

import com.zzpj.exceptions.IngredientNotFoundException;
import com.zzpj.model.entities.Ingredient;

import java.io.IOException;

public interface IngredientServiceInterface {
    Ingredient getIngredientsByKeyword(String keyword) throws IngredientNotFoundException, IOException;
}
