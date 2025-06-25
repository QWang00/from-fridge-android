package com.recipes.from_fridge.ui.search.recipe_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.recipes.from_fridge.model.RecipeDetail;
import com.recipes.from_fridge.model.RecipePreview;
import com.recipes.from_fridge.repository.RecipeRepository;
import java.util.List;

public class RecipeDetailViewModel extends ViewModel {

    private final RecipeRepository repository;

    private final MutableLiveData<RecipePreview> selectedRecipe = new MutableLiveData<>();
    private final MutableLiveData<RecipeDetail> recipeDetail = new MutableLiveData<>();

    public RecipeDetailViewModel(RecipeRepository repository) {
        this.repository = repository;
    }

    public void setRecipe(RecipePreview recipe) {
        selectedRecipe.setValue(recipe);
        loadRecipeDetail();
    }

    public LiveData<RecipePreview> getSelectedRecipe() {
        return selectedRecipe;
    }

    public LiveData<RecipeDetail> getRecipeDetail() {
        return recipeDetail;
    }

    private void loadRecipeDetail() {
        RecipePreview preview = selectedRecipe.getValue();
        if (preview == null || preview.getId() == null) return;

        int recipeId = preview.getId();

        List<String> matchedIngredients = preview.getMatchedIngredients();

        repository.getRecipeDetail(recipeId, matchedIngredients)
                .observeForever(detail -> recipeDetail.setValue(detail));
    }

}
