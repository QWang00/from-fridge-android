package com.recipes.from_fridge.ui.search;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.recipes.from_fridge.model.Ingredient;
import com.recipes.from_fridge.model.RecipePreview;
import com.recipes.from_fridge.repository.RecipeRepository;
import com.recipes.from_fridge.util.AppContextProvider;
import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<List<Ingredient>> selectedIngredients = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<RecipePreview>> matchedRecipes = new MutableLiveData<>();

    private final RecipeRepository recipeRepository;

    public SearchViewModel(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public SearchViewModel() {
        this.recipeRepository = null;
    }

    public LiveData<List<Ingredient>> getSelectedIngredients() {
        return selectedIngredients;
    }

    public LiveData<List<RecipePreview>> getMatchedRecipes() {
        return matchedRecipes;
    }

    public void addIngredient(Ingredient ingredient) {
        List<Ingredient> current = selectedIngredients.getValue();
        if (current == null) current = new ArrayList<>();

        if (current.size() >= 5) {
            showToast("You can select up to 5 ingredients.");
            return;
        }

        for (Ingredient i : current) {
            if (i.getId().equals(ingredient.getId())) {
                showToast("You've already selected this ingredient.");
                return;
            }
        }

        current.add(ingredient);
        selectedIngredients.setValue(current);
    }

    public void removeIngredient(Ingredient ingredient) {
        List<Ingredient> current = selectedIngredients.getValue();
        if (current == null) return;

        current.removeIf(i -> i.getId().equals(ingredient.getId()));
        selectedIngredients.setValue(current);
    }

    public void searchRecipes() {
        List<Ingredient> selected = selectedIngredients.getValue();
        if (selected == null || selected.isEmpty() || recipeRepository == null) {
            matchedRecipes.setValue(new ArrayList<>());
            return;
        }

        List<String> ingredientNames = new ArrayList<>();
        for (Ingredient i : selected) {
            ingredientNames.add(i.getName());
        }

        recipeRepository.searchRecipesByIngredients(ingredientNames)
                .observeForever(recipePreviews -> {
                    matchedRecipes.setValue(recipePreviews != null ? recipePreviews : new ArrayList<>());
                });
    }


    public void clearSelectedIngredients() {
        selectedIngredients.setValue(new ArrayList<>());
    }

    private void showToast(String message) {
        Toast.makeText(AppContextProvider.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
