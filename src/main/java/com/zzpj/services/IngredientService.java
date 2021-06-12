package com.zzpj.services;

import com.zzpj.exceptions.IngredientNotFoundException;
import com.zzpj.exceptions.URLNotFoundException;
import com.zzpj.model.entities.Ingredient;
import com.zzpj.model.parsers.IngredientJsonParser;
import com.zzpj.repositories.IngredientRepository;
import com.zzpj.security.SecurityConstants;
import com.zzpj.services.interfaces.IngredientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class IngredientService implements IngredientServiceInterface {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getIngredientsByKeyword(String keyword) throws URLNotFoundException, IngredientNotFoundException, IOException {
        String requestUrl = getRequest(keyword);
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        if (status == 404) {
            throw new URLNotFoundException("404 Error thrown");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return IngredientJsonParser.parse(content.toString());
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public String getRequest(String keyword) {
        return "https://api.edamam.com/api/food-database/v2/parser?" +
            "app_id=" + SecurityConstants.API_APP_ID +
            "&app_key=" + SecurityConstants.API_APPLICATION_KEYS +
            "&ingr=" + replaceSpacesInKeyword(keyword) +
            "&categoryLabel=food";
    }

    public String replaceSpacesInKeyword(String keyword) {
        return keyword.replaceAll(" ", "%20");
    }
}
