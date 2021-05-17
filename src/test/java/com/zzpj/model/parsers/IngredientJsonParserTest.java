package com.zzpj.model.parsers;

import com.zzpj.exceptions.IngredientNotFoundException;
import com.zzpj.exceptions.URLNotFoundException;
import com.zzpj.model.entities.Ingredient;
import com.zzpj.services.IngredientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IngredientJsonParserTest {

    @Test
    void parse() throws URLNotFoundException, IOException, IngredientNotFoundException {
        IngredientService is = new IngredientService();

        Ingredient ingredient = is.getIngredientsByKeyword("Mint leaf");

        Assertions.assertEquals("food_bxl4xoga4owdkeay51sy8anesxj5", ingredient.getId());
        Assertions.assertEquals("Mint Leaf", ingredient.getName());
        Assertions.assertEquals(1, ingredient.getQuantity());
        Assertions.assertEquals(44, ingredient.getCalories());
        Assertions.assertEquals(3, ingredient.getProtein());
        Assertions.assertEquals(8, ingredient.getCarbohydrates());
        Assertions.assertEquals(1, ingredient.getFats());
    }
}