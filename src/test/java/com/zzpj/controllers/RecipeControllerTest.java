package com.zzpj.controllers;

import com.zzpj.exceptions.*;
import com.zzpj.model.DTOs.CustomIngredientDTO;
import com.zzpj.model.DTOs.RecipeDetailsDTO;
import com.zzpj.model.DTOs.RecipeGeneralDTO;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.Ingredient;
import com.zzpj.model.entities.Recipe;
import com.zzpj.model.mappers.RecipeMapper;
import com.zzpj.services.AccountService;
import com.zzpj.services.IngredientService;
import com.zzpj.services.interfaces.RecipeServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

    private final List<Recipe> recipes = new ArrayList<>();
    @Spy
    private final Recipe recipe = new Recipe();
    @Spy
    private final Recipe recipeSecond = new Recipe();
    private final long id1 = 1L;
    private long id2 = 2L;
    private final String name = "Name";
    private final String login = "user1";
    private final String description = "Description";
    private final List<Ingredient> recipeIngredients = new ArrayList<>();
    private final List<Ingredient> changedIngredients = new ArrayList<>();
    private float rating = 2.0F;
    private float newRating = 3.0F;
    private int ratingCount = 30;
    private String tags = "Tags";
    private byte[] img = null;
    private int servings = 2;
    private int calories = 1500;
    private long prepareTime = 45L;
    private String difficulty = "ezz";
    private CustomIngredientDTO customIngredientDTO = new CustomIngredientDTO("mint", 1.0);
    private Account acc = new Account();
    @Spy
    private final Ingredient ingredient1 = new Ingredient();
    @Spy
    private final Ingredient ingredient2 = new Ingredient();
    @Spy
    private final Ingredient ingredient3 = new Ingredient();
    @Mock
    private RecipeServiceInterface recipeService;
    @Mock
    private AccountService accountService;
    @Mock
    private IngredientService ingredientService;
    @Spy
    private Principal principal;

    @InjectMocks
    private RecipeController recipeController;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
        when(principal.getName()).thenReturn("Login");

        when(ingredient1.getName()).thenReturn("Mint");
        when(ingredient1.getQuantity()).thenReturn(1.0);
        when(ingredient1.getCalories()).thenReturn(44.0);
        when(ingredient1.getProtein()).thenReturn(3.29);
        when(ingredient1.getCarbohydrates()).thenReturn(8.41);
        when(ingredient1.getFats()).thenReturn(0.73);
        when(ingredient2.getName()).thenReturn("Mint");
        when(ingredient2.getQuantity()).thenReturn(1.0);
        when(ingredient2.getCalories()).thenReturn(44.0);
        when(ingredient2.getProtein()).thenReturn(3.29);
        when(ingredient2.getCarbohydrates()).thenReturn(8.41);
        when(ingredient2.getFats()).thenReturn(0.73);
        when(ingredient3.getName()).thenReturn("Mint");
        when(ingredient3.getQuantity()).thenReturn(1.0);
        when(ingredient3.getCalories()).thenReturn(44.0);
        when(ingredient3.getProtein()).thenReturn(3.29);
        when(ingredient3.getCarbohydrates()).thenReturn(8.41);
        when(ingredient3.getFats()).thenReturn(0.73);
        recipeIngredients.add(ingredient1);
        recipeIngredients.add(ingredient2);
        when(recipe.getId()).thenReturn(id1);
        when(recipe.getName()).thenReturn(name);
        when(recipe.getAuthorLogin()).thenReturn(login);
        when(recipe.getDescription()).thenReturn(description);
        when(recipe.getRecipeIngredients()).thenReturn(recipeIngredients);
        when(recipe.getRating()).thenReturn(rating);
        when(recipe.getRatingsCount()).thenReturn(ratingCount);
        when(recipe.getRecipeTags()).thenReturn(tags);
        when(recipe.getImage()).thenReturn(img);
        when(recipe.getServings()).thenReturn(servings);
        when(recipe.getCalories()).thenReturn(calories);
        when(recipe.getPrepareTimeInMinutes()).thenReturn(prepareTime);
        when(recipe.getDifficulty()).thenReturn(difficulty);
        when(recipeSecond.getId()).thenReturn(id2);
        when(recipeSecond.getName()).thenReturn(name);
        when(recipeSecond.getAuthorLogin()).thenReturn(login);
        when(recipeSecond.getDescription()).thenReturn(description);
        when(recipeSecond.getRecipeIngredients()).thenReturn(recipeIngredients);
        when(recipeSecond.getRating()).thenReturn(rating);
        when(recipeSecond.getRatingsCount()).thenReturn(ratingCount);
        when(recipeSecond.getRecipeTags()).thenReturn(tags);
        when(recipeSecond.getImage()).thenReturn(img);
        when(recipeSecond.getServings()).thenReturn(servings);
        when(recipeSecond.getCalories()).thenReturn(calories);
        when(recipeSecond.getPrepareTimeInMinutes()).thenReturn(prepareTime);
        when(recipeSecond.getDifficulty()).thenReturn(difficulty);
        recipes.add(recipe);
    }

    @Test
    void createRecipe() {
        RecipeDetailsDTO newRecipe = new RecipeDetailsDTO();
        Mockito.doAnswer(invocationOnMock -> {
            recipes.add(invocationOnMock.getArgument(0));
            return null;
        }).when(recipeService).createRecipe(any());

        assertEquals(1, recipes.size());
        assertDoesNotThrow(() -> recipeController.createRecipe(newRecipe, principal));
        assertEquals(HttpStatus.OK, (recipeController.createRecipe(newRecipe, principal)).getStatusCode());

        verify(recipeService, times(2)).createRecipe(any());
        assertEquals(3, recipes.size());
    }

    @Test
    void getRecipeById() throws RecipeDoesNotExistException {
        when(recipeService.getRecipeById(0L)).thenReturn(recipe);
        assertDoesNotThrow(() -> recipeController.getRecipeById(0L));
        assertEquals(RecipeMapper.entityToDetailsDTO(recipe), recipeController.getRecipeById(0L).getBody());
        assertEquals(HttpStatus.OK, (recipeController.getRecipeById(0L).getStatusCode()));
    }

    @Test
    void getAllRecipes() {
        when(recipeService.getAllRecipes()).thenReturn(recipes);
        assertDoesNotThrow(() -> recipeController.getAllRecipes());
        List<RecipeGeneralDTO> list = recipes.stream().map(RecipeMapper::entityToGeneralDTO).collect(Collectors.toList());
        assertEquals(list, recipeController.getAllRecipes().getBody());
        assertEquals(HttpStatus.OK, (recipeController.getAllRecipes().getStatusCode()));
    }

    @Test
    void getRecommendationBasedOnLikings() throws AccountDoesNotExistException {
        when(accountService.getAccountByLogin("Login")).thenReturn(acc);
        List<Recipe> recommended = List.of(recipe, recipeSecond);
        when(recipeService.getRecommendationBasedOnLikings(acc, List.of("meat","vegan"))).thenReturn(recommended);

        assertEquals(recommended.stream().map(RecipeMapper::entityToGeneralDTO).collect(Collectors.toList()),
                recipeController.getRecommendationBasedOnLikings(List.of("meat","vegan"), principal).getBody());
        assertEquals(HttpStatus.OK, (recipeController.getRecommendationBasedOnLikings(List.of("meat","vegan"), principal).getStatusCode()));
    }

    @Test
    void getAllRecipesForAccount() {
        when(recipeService.getAllRecipesForAccount(login)).thenReturn(recipes);
        assertDoesNotThrow(() -> recipeController.getAllRecipesForAccount(login));
        List<RecipeGeneralDTO> list = recipes.stream()
            .map(RecipeMapper::entityToGeneralDTO).collect(Collectors.toList());
        assertEquals(list, recipeController.getAllRecipesForAccount(login).getBody());
        assertEquals(HttpStatus.OK, (recipeController.getAllRecipesForAccount(login).getStatusCode()));
    }

    @Test
    void getFavouriteRecipesForAccount() {
        List<Recipe> favorites = new ArrayList<>();
        favorites.add(recipe);
        favorites.add(recipeSecond);
        when(recipeService.getFavouriteRecipesForAccount(login)).thenReturn(favorites);
        assertDoesNotThrow(() -> recipeController.getFavouriteRecipesForAccount(login));
        List<RecipeGeneralDTO> list = favorites.stream()
            .map(RecipeMapper::entityToGeneralDTO).collect(Collectors.toList());
        assertEquals(list, recipeController.getFavouriteRecipesForAccount(login).getBody());
    }

    @Test
    void deleteRecipe() throws RecipeDoesNotExistException {
        Mockito.doAnswer(invocation -> {
            if (recipes.size() > 0) {
                recipes.remove(recipes.size() - 1);
            }
            return null;
        }).when(recipeService).deleteRecipe(any());
        assertEquals(1, recipes.size());
        assertDoesNotThrow(() -> recipeController.deleteRecipe(any()));
        assertEquals(HttpStatus.OK, (recipeController.deleteRecipe(any()).getStatusCode()));
        assertEquals(0, recipes.size());

    }

    @Test
    void updateRecipe() throws RecipeDoesNotExistException, NotAnAuthorException {
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setDescription(description);
        updatedRecipe.setServings(6);

        when(recipeService.getAllRecipes()).thenReturn(recipes);
        when(recipeService.getRecipeById(any())).thenReturn(updatedRecipe);
        assertEquals(description, updatedRecipe.getDescription());

        Mockito.doAnswer(invocation -> {
            updatedRecipe.setDescription("Nowy");
            updatedRecipe.setServings(7);
            return null;
        }).when(recipeService).updateRecipe(any(), any(), any());

        assertDoesNotThrow(() -> recipeController.updateRecipe(id1, RecipeMapper.entityToGeneralDTO(recipe), principal));
        assertEquals(HttpStatus.OK, (recipeController.updateRecipe(id1, RecipeMapper.entityToGeneralDTO(recipe), principal).getStatusCode()));
        assertEquals("Nowy", updatedRecipe.getDescription());
        assertEquals(7, updatedRecipe.getServings());
    }

    @Test
    void addIngredient() throws IngredientNotFoundException, URLNotFoundException, IOException, NotAnAuthorException, RecipeDoesNotExistException {
        when(ingredientService.getIngredientsByKeyword("mint")).thenReturn(ingredient1);
        assertEquals(2, recipes.get(0).getRecipeIngredients().size());

        Mockito.doAnswer(invocation -> {
            recipes.get(0).getRecipeIngredients().add(ingredient1);
            return null;
        }).when(recipeService).addIngredient(any(), any(), any());

        assertDoesNotThrow(() -> recipeController.addIngredient(id1, customIngredientDTO, principal));
        assertEquals(HttpStatus.OK, recipeController.addIngredient(id1, customIngredientDTO, principal).getStatusCode());
        assertEquals(4, recipes.get(0).getRecipeIngredients().size());
    }

    @Test
    void removeIngredientFromRecipe() throws IngredientNotFoundException, NotAnAuthorException, RecipeDoesNotExistException {
        assertEquals(2, recipes.get(0).getRecipeIngredients().size());

        Mockito.doAnswer(invocation -> {
            recipe.getRecipeIngredients().remove(0);
            return null;
        }).when(recipeService).removeIngredientFromRecipe(any(), any(), any());

        assertDoesNotThrow(() -> recipeController.removeIngredientFromRecipe(id1, "mąka", principal));
        assertEquals(HttpStatus.OK, recipeController.removeIngredientFromRecipe(id1, "mąka", principal).getStatusCode());
        assertEquals(0, recipes.get(0).getRecipeIngredients().size());
    }

    @Test
    void addRatingToRecipe() throws RecipeDoesNotExistException {
        Recipe r = new Recipe();
        r.setId(2115L);
        r.setRating(5.0f);
        r.setRatingsCount(1);

        assertEquals(5.0f, r.getRating(), 0.001);
        assertEquals(1, r.getRatingsCount());

        Mockito.doAnswer(invocation -> {
            r.setRatingsCount(2);
            r.setRating((5.0F * 2 + 3.0f) / (2 + 1));
            return null;
        }).when(recipeService).addRatingToRecipe(r.getId(), 3.0f);

        assertDoesNotThrow(() -> recipeController.addRatingToRecipe(2115L, 3.0f));
        assertEquals(HttpStatus.OK, recipeController.addRatingToRecipe(2115L, 3.0f).getStatusCode());

        assertEquals(4.3f, r.getRating(), 0.1);
        assertEquals(2, r.getRatingsCount());
    }

}
