package com.zzpj.services;

import com.zzpj.exceptions.RecipeDoesNotExistException;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.Ingredient;
import com.zzpj.model.entities.Recipe;
import com.zzpj.repositories.AccountRepository;
import com.zzpj.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private RecipeService recipeService;
    @Spy
    private final Recipe recipe = new Recipe();
    @Spy
    private final Recipe newRecipe = new Recipe();
    @Spy
    private final Ingredient ingredient = new Ingredient();
    @Spy
    private final Ingredient newIngredient = new Ingredient();
    @Spy
    private final List<Ingredient> ingredients = new ArrayList<>();
    @Spy
    private final Recipe updatedRecipe = new Recipe();

    private final Account account = new Account();

    private final String name = "Bułeczki";
    private String description = "Smaczne bułeczki";
    private int calories = 500;
    private final List<Recipe> recipes = new ArrayList<>();
    private Long id = 1L;
    private String ingredientName = "Mąka";
    private String newIngredientName = "Mleko";
    private Float rating = 5F;
    private int ratingsCount = 5;


    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);
        Mockito.when(recipe.getName()).thenReturn(name);
        Mockito.when(recipe.getDescription()).thenReturn(description);
        Mockito.when(recipe.getCalories()).thenReturn(calories);
        Mockito.when(recipe.getRecipeIngredients()).thenReturn(ingredients);
        Mockito.when(recipe.getId()).thenReturn(id);
        Mockito.when(recipe.getRating()).thenReturn(rating);
        Mockito.when(recipe.getRatingsCount()).thenReturn(ratingsCount);
        Mockito.when(recipe.getAuthorLogin()).thenReturn("Login");
        recipes.add(recipe);
        Mockito.when(ingredient.getName()).thenReturn(ingredientName);
        ingredients.add(ingredient);
        Mockito.when(newRecipe.getName()).thenReturn("Pączek");
        account.setLogin("Login");
        account.setFavouriteRecipes(List.of(recipe, newRecipe));
    }


    @Test
    void createRecipe() {

        String newName = "Chlebek";
        newRecipe.setName(newName);
        Long newTime = 120L;
        newRecipe.setPrepareTimeInMinutes(newTime);

        Mockito.doAnswer(invocation -> {
            recipes.add(invocation.getArgument(0));
            return null;
        }).when(recipeRepository).save(newRecipe);

        Mockito.when(newRecipe.getName()).thenReturn(newName);
        Mockito.when(newRecipe.getPrepareTimeInMinutes()).thenReturn(newTime);

        assertEquals(1, recipes.size());
        assertDoesNotThrow(() -> recipeService.createRecipe(newRecipe));
        Mockito.verify(recipeRepository).save(newRecipe);
        Assertions.assertEquals(2, recipes.size());
        Assertions.assertEquals(newRecipe, recipes.get(1));
        Assertions.assertEquals(newName, recipes.get(1).getName());
        Assertions.assertEquals(newTime, recipes.get(1).getPrepareTimeInMinutes());

    }

    @Test
    void getRecipeById() throws RecipeDoesNotExistException {
        Mockito.doAnswer(invocation -> recipe).when(recipeRepository).findRecipeById(id);

        assertDoesNotThrow(() -> recipeService.getRecipeById(id));
        Recipe recipeById = recipeService.getRecipeById(id);

        assertEquals(name, recipeById.getName());
    }

    @Test
    void getRecipeByIdException() {
        assertThrows(RecipeDoesNotExistException.class, () -> recipeService.getRecipeById(123L));
    }

    @Test
    void getAllRecipes() {
        Mockito.doAnswer(invocation -> recipes).when(recipeRepository).findAll();

        assertDoesNotThrow(() -> recipeService.getAllRecipes());
        assertEquals(1, recipeService.getAllRecipes().size());
    }

    @Test
    void deleteRecipe() {
        Mockito.doAnswer(invocation -> {
            recipes.remove(recipe);
            return null;
        }).when(recipeRepository).delete(recipe);

        assertEquals(1, recipes.size());
        assertDoesNotThrow(() -> recipeService.deleteRecipe(id));
        Mockito.verify(recipeRepository).delete(recipe);
        assertEquals(0, recipes.size());

    }

    @Test
    void deleteRecipeException() {
        assertThrows(RecipeDoesNotExistException.class, () -> recipeService.deleteRecipe(123L));
    }

    @Test
    void updateRecipe() throws RecipeDoesNotExistException {
        updatedRecipe.setName("Bagietka");
        updatedRecipe.setCalories(300);

        Mockito.doAnswer(invocation -> {
            Recipe recipe = invocation.getArgument(0);
            recipe.setName("Bagietka");
            recipe.setCalories(300);
            return null;
        }).when(recipeRepository).save(any());

        Mockito.when(recipeRepository.findRecipeById(id)).thenReturn(updatedRecipe);

        assertDoesNotThrow(() -> recipeService.updateRecipe(id, updatedRecipe));
        Mockito.verify(recipeRepository).save(updatedRecipe);
        Recipe recipeById = recipeService.getRecipeById(id);

        assertEquals("Bagietka", recipeById.getName());
        assertEquals(300, recipeById.getCalories());
    }

    @Test
    void updateRecipeException(){
        assertThrows(RecipeDoesNotExistException.class, () -> recipeService.updateRecipe(123L, updatedRecipe));
    }

    @Test
    void addIngredient() throws RecipeDoesNotExistException {
        newIngredient.setName(newIngredientName);
        ingredients.add(newIngredient);
        updatedRecipe.setRecipeIngredients(ingredients);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

        Mockito.doAnswer(invocation -> {
            Recipe recipe = invocation.getArgument(0);
            recipe.setRecipeIngredients(ingredients);
            return null;
        }).when(recipeRepository).save(any());

        Mockito.when(recipeRepository.findRecipeById(id)).thenReturn(updatedRecipe);

        Recipe recipeById = recipeService.getRecipeById(id);
        assertEquals(2, recipeById.getRecipeIngredients().size());
        assertDoesNotThrow(() -> recipeService.addIngredient(id, newIngredient));
        Mockito.verify(recipeRepository).save(any());
        assertEquals(newIngredientName, recipe.getRecipeIngredients().get(1).getName());
    }

    @Test
    void addIngredientException() {
        assertThrows(RecipeDoesNotExistException.class, () -> recipeService.addIngredient(123L, ingredient));
    }

    @Test
    void addRatingToRecipe() throws RecipeDoesNotExistException {
        updatedRecipe.setRating(6F);
        updatedRecipe.setRatingsCount(6);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

        Mockito.doAnswer(invocation -> {
            Recipe recipe = invocation.getArgument(0);
            recipe.setRatingsCount(6);
            recipe.setRating((5F * 5 + 11) / (5 + 1));
            return null;
        }).when(recipeRepository).save(any());

        Mockito.when(recipeRepository.findRecipeById(id)).thenReturn(updatedRecipe);
        assertDoesNotThrow(() -> recipeService.addRatingToRecipe(id, 11));
        Mockito.verify(recipeRepository).save(any());

        Recipe recipeById = recipeService.getRecipeById(id);
        assertEquals(6, recipeById.getRatingsCount());
        assertEquals(6F, recipeById.getRating());
    }

    @Test
    void addRatingException() {
        assertThrows(RecipeDoesNotExistException.class, () -> recipeService.addRatingToRecipe(123L, 3F));
    }

    @Test
    void getAllRecipesForAccount() {
        Mockito.doAnswer(invocation -> recipes).when(recipeRepository).findAll();

        assertDoesNotThrow(() -> recipeService.getAllRecipesForAccount("Login"));
        assertEquals(1, recipeService.getAllRecipesForAccount("Login").size());
    }

    @Test
    void getFavouriteRecipesForAccount() {
        Mockito.when(accountRepository.findByLogin("Login")).thenReturn(account);

        assertDoesNotThrow(() -> recipeService.getFavouriteRecipesForAccount("Login"));
        assertEquals(2, recipeService.getFavouriteRecipesForAccount("Login").size());
    }
}
