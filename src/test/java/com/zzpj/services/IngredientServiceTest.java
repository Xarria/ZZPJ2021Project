package com.zzpj.services;

import com.zzpj.exceptions.IngredientNotFoundException;
import com.zzpj.security.SecurityConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class IngredientServiceTest {

    @Test
    void getIngredientsByKeyword() throws IOException, IngredientNotFoundException {
        IngredientService ing = new IngredientService();
        ing.getIngredientsByKeyword("mint leaves");
    }

    @Test
    void getRequest() {
        IngredientService ing = new IngredientService();
        String longKeywordWithSpaces = "pozdrawiam osoby ktore to przeczytaja. Zapraszam na jakas Perelke w plenerze czy cos jak bedzie cieplej 2115";
        String createdRequest = ing.getRequest(longKeywordWithSpaces);
        Assertions.assertEquals("https://api.edamam.com/api/food-database/v2/parser?" +
            "app_id=" + SecurityConstants.API_APP_ID +
            "&app_key=" + SecurityConstants.API_APPLICATION_KEYS +
            "&ingr=pozdrawiam%20osoby%20ktore%20to%20przeczytaja.%20Zapraszam%20na%20jakas%20Perelke%20w%20plenerze%20czy%20cos%20jak%20bedzie%20cieplej%202115" +
            "&nutrition-type=logging" +
            "&categoryLabel=food", createdRequest);
    }

    @Test
    void replaceSpacesInKeyword() {
        IngredientService ing = new IngredientService();
        String test = ing.replaceSpacesInKeyword("cie ka we czy za dzia ła");
        Assertions.assertEquals("cie%20ka%20we%20czy%20za%20dzia%20ła", test);
    }
}