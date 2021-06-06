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
        i.setId(food.getFoodId());
        i.setName(food.getLabel());
        i.setQuantity(response.getParsed().get(0).getQuantity());
        i.setCalories(Math.round(food.getNutrients().getENERC_KCAL()));
        i.setProtein(Math.round(food.getNutrients().getPROCNT()));
        i.setCarbohydrates(Math.round(food.getNutrients().getCHOCDF()));
        i.setFats(Math.round(food.getNutrients().getFAT()));

        return i;
    }

}

