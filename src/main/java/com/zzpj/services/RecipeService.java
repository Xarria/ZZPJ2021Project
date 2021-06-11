package com.zzpj.services;

import com.zzpj.exceptions.RecipeDoesNotExistException;
import com.zzpj.model.entities.Account;
import com.zzpj.model.entities.Ingredient;
import com.zzpj.model.entities.Recipe;
import com.zzpj.repository.AccountRepository;
import com.zzpj.repository.IngredientRepository;
import com.zzpj.repository.RecipeRepository;
import com.zzpj.services.interfaces.RecipeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RecipeService implements RecipeServiceInterface {

    private final RecipeRepository recipeRepository;

    private final AccountRepository accountRepository;

    private final IngredientRepository ingredientRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, AccountRepository accountRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.accountRepository = accountRepository;
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public void createRecipe(Recipe recipe) {
        recipe.getRecipeIngredients().forEach(ingredientRepository::save);
        recipeRepository.save(recipe);
    }

    @Override
    public Recipe getRecipeById(Long id) throws RecipeDoesNotExistException {
        if (recipeRepository.findAll().stream().noneMatch(r -> r.getId().equals(id))) {
            throw new RecipeDoesNotExistException("Recipe with id " + id + " was not found.");
        }
        return recipeRepository.findRecipeById(id);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> getAllRecipesForAccount(String login) {
        return recipeRepository.findAll().stream().filter(r -> r.getAuthorLogin().equals(login)).collect(Collectors.toList());
    }

    @Override
    public List<Recipe> getFavouriteRecipesForAccount(String login) {
        return accountRepository.findByLogin(login).getFavouriteRecipes();
    }

    @Override
    public List<Recipe> getRecommendationBasedOnLikings(Account account, List<String> unwantedTags) {
        List<String> favouriteRecipesTags = account.getFavouriteRecipes().stream()
                .map(r -> List.of(r.getRecipeTags().split(",")))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<String> filteredTags = favouriteRecipesTags.stream().filter(r -> !unwantedTags.contains(r)).collect(Collectors.toList());

        HashMap<String, Integer> tagsQuantities = new HashMap<>();

        for (String tag : filteredTags) {
            if (tagsQuantities.containsKey(tag)) {
                tagsQuantities.put(tag, tagsQuantities.get(tag) + 1);
            } else {
                tagsQuantities.put(tag, 1);
            }
        }

        List<String> tagsSortedByQantity = sortKeysByValue(tagsQuantities, false);

        List<String> mostLiked = new ArrayList<>();

        for (int i = 0; i < tagsSortedByQantity.size(); i++) {
            if(i < 5){
                mostLiked.add(tagsSortedByQantity.get(i));
            }
            else{
                break;
            }
        }

        List<Recipe> allRecipes = recipeRepository.findAll();

        List<Recipe> notFavouriteRecipes = allRecipes.stream().filter(r -> !account.getFavouriteRecipes().contains(r)).collect(Collectors.toList());
        Set<Recipe> recommended = new HashSet<>();

        for (Recipe notFavouriteRecipe : notFavouriteRecipes) {
            for (String s : mostLiked) {
                if (notFavouriteRecipe.getRecipeTags().contains(s)) {
                    recommended.add(notFavouriteRecipe);
                }
            }
        }

        return List.copyOf(recommended);
    }

    private List<String> sortKeysByValue(HashMap<String, Integer> map, boolean ascending)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort((o1, o2) -> {
            if (ascending) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        List<String> keysSortedByValue = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : list)
        {
            keysSortedByValue.add(entry.getKey());
        }
        return keysSortedByValue;
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
        if (recipeRepository.findAll().stream().noneMatch(r -> r.getId().equals(id))) {
            throw new RecipeDoesNotExistException("Recipe with id " + id + " was not found.");
        }
        Recipe recipe = recipeRepository.findRecipeById(id);
        recipe.setName(updatedRecipe.getName());
        recipe.setDescription(updatedRecipe.getDescription());
        recipe.setCalories(updatedRecipe.getCalories());
        recipe.setRecipeTags(updatedRecipe.getRecipeTags());
        recipe.setRecipeIngredients(updatedRecipe.getRecipeIngredients());
        recipe.setPrepareTimeInMinutes(updatedRecipe.getPrepareTimeInMinutes());
        recipe.setDifficulty(updatedRecipe.getDifficulty());
        recipe.setServings(updatedRecipe.getServings());
        recipe.setImage(updatedRecipe.getImage());
        recipeRepository.save(recipe);
    }

    @Override
    public void addIngredient(Long recipeId, Ingredient ingredient) throws RecipeDoesNotExistException {
        if (recipeRepository.findAll().stream().noneMatch(r -> r.getId().equals(recipeId))) {
            throw new RecipeDoesNotExistException("Recipe with id " + recipeId + " was not found.");
        }
        Recipe recipe = recipeRepository.findRecipeById(recipeId);

        List<Ingredient> ingredients = recipe.getRecipeIngredients();
        ingredients.add(ingredient);
        recipe.setRecipeIngredients(ingredients);
        recipeRepository.save(recipe);
    }

    @Override
    public String sendRecipeByMail(Long id) throws RecipeDoesNotExistException {
        Recipe recipe = recipeRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecipeDoesNotExistException("Recipe with id " + id + " was not found."));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>").append(recipe.getName().toUpperCase()).append("</b><br>").append("<b> Ingredients: </b> <br>");
        for (int i = 0; i < recipe.getRecipeIngredients().size(); i++) {
            stringBuilder.append(recipe.getRecipeIngredients().get(i).getName())
                    .append(": ")
                    .append(recipe.getRecipeIngredients().get(i).getQuantity())
                    .append("g <br>");
        }
        stringBuilder.append("<br>");
        stringBuilder.append("<b> Steps: </b><br>");
        stringBuilder.append(recipe.getDescription()).append("<br>");
        return stringBuilder.toString();
    }

    @Override
    public void addRatingToRecipe(Long id, float rating) throws RecipeDoesNotExistException {
        Recipe recipe = recipeRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RecipeDoesNotExistException("Recipe with id " + id + " was not found."));

        float currentRating = recipe.getRating();
        int ratingsCount = recipe.getRatingsCount();

        recipe.setRating((currentRating * ratingsCount + rating) / (ratingsCount + 1));
        recipe.setRatingsCount(recipe.getRatingsCount() + 1);

        recipeRepository.save(recipe);
    }

    @Override
    public String getShoppingList(List<Long> recipes) {
        List<Recipe> filteredRecipes = recipeRepository.findAll().stream()
                .filter(r -> recipes.contains(r.getId())).collect(Collectors.toList());
        Map<String, Double> ingredients = new HashMap<>();
        for (Recipe recipe : filteredRecipes) {
            for (Ingredient i : recipe.getRecipeIngredients()) {
                if (ingredients.containsKey(i.getName())) {
                    ingredients.put(i.getName(), ingredients.get(i.getName()) + i.getQuantity());
                } else {
                    ingredients.put(i.getName(), i.getQuantity());
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>Your shopping list: </b> <br> <br>");
        for (Map.Entry<String, Double> entry : ingredients.entrySet()) {
            stringBuilder.append("Name: ")
                    .append(entry.getKey())
                    .append(", Quantity: ")
                    .append(entry.getValue())
                    .append("g <br>");
        }
        return stringBuilder.toString();
    }


}


