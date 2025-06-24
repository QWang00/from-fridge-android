package com.recipes.from_fridge.ui.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.recipes.from_fridge.repository.RecipeRepository;

public class SearchViewModelFactory implements ViewModelProvider.Factory {
    private final RecipeRepository repository;

    public SearchViewModelFactory(RecipeRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

