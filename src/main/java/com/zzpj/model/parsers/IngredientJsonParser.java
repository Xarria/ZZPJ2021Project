package com.zzpj.model.parsers;

import com.google.gson.Gson;
import com.zzpj.exceptions.IngredientNotFoundException;
import com.zzpj.model.entities.Ingredient;
import lombok.Data;

import java.util.List;

@Data
class Food {
    private String foodId;
    private String label;
    private Nutrients nutrients;
}

@Data
class Nutrients {
    private float ENERC_KCAL;
    private float PROCNT;
    private float FAT;
    private float CHOCDF;
}

@Data
class Item {
    private Food food;
    private double quantity;
}

@Data
class Response {
    private List<Item> parsed;
}

public class IngredientJsonParser {

    public static Ingredient parse(String json) throws IngredientNotFoundException {
        Gson gson = new Gson();
        Response response = gson.fromJson(json, Response.class);

        if (response.getParsed().size() == 0) {
            throw new IngredientNotFoundException("No ingredient was found");
        }
        Food food = response.getParsed().get(0).getFood();

        Ingredient i = new Ingredient();
        i.setName(food.getLabel());
        i.setCalories((double) food.getNutrients().getENERC_KCAL());
        i.setProtein((double) food.getNutrients().getPROCNT());
        i.setCarbohydrates((double) food.getNutrients().getCHOCDF());
        i.setFats((double) food.getNutrients().getFAT());
        i.setQuantity(1.0);

        return i;
    }

}

