package com.recipes.from_fridge.ui.search.recipe_detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.recipes.from_fridge.repository.RecipeRepository;

public class RecipeDetailViewModelFactory implements ViewModelProvider.Factory {

    private final RecipeRepository repository;

    public RecipeDetailViewModelFactory(RecipeRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipeDetailViewModel.class)) {
            return (T) new RecipeDetailViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
