package com.zzpj.services;

import com.zzpj.exceptions.RecipeDoesNotExistException;
import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.Recipe;
import com.zzpj.model.mappers.RecipeMapper;
import com.zzpj.repository.RecipeRepository;
import com.zzpj.services.interfaces.RecipeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService implements RecipeServiceInterface {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public void createRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public RecipeDetailsDTO getRecipeById(Long id) throws RecipeDoesNotExistException {
        Recipe recipe = recipeRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecipeDoesNotExistException("Recipe with id " + id + " was not found."));
        return RecipeMapper.entityToDetailsDTO(recipe);
    }

    @Override
    public List<RecipeGeneralDTO> getAllRecipes() {
        return recipeRepository.findAll().stream().map(RecipeMapper::entityToGeneralDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteRecipe(Long id) throws RecipeDoesNotExistException {
        Recipe recipe = recipeRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecipeDoesNotExistException("Recipe with id " + id + " was not found."));
        recipeRepository.delete(recipe);
    }

    @Override
    public void updateRecipe(Long id, Recipe updatedRecipe) throws RecipeDoesNotExistException {
        Recipe recipe = recipeRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecipeDoesNotExistException("Recipe with id " + id + " was not found."));
        //TODO ustalić zmieniane pola
        recipe.setName(updatedRecipe.getName());
        recipe.setDescription(updatedRecipe.getDescription());
        recipe.setCalories(updatedRecipe.getCalories());
        recipeRepository.save(recipe);
    }

    @Override
    public void saveRecipeToFilesystem(Long id, String filename) throws IOException, RecipeDoesNotExistException {
        //TODO ustalić formę zapisywania
        Recipe recipe = recipeRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecipeDoesNotExistException("Recipe with id " + id + " was not found."));

        FileOutputStream fileOut = new FileOutputStream(filename + ".txt");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(recipe);
        objectOut.close();
    }

    @Override
    public void addRatingToRecipe(Long id, float rating) throws RecipeDoesNotExistException {
        Recipe recipe = recipeRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecipeDoesNotExistException("Recipe with id " + id + " was not found."));

        float currentRating = recipe.getRating();
        //TODO ustalić jak liczymy ocenę
        recipe.setRating(currentRating);

        recipeRepository.save(recipe);
    }
}


