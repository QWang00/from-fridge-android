package com.recipes.from_fridge.ui.search.search_result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.chip.Chip;
import com.recipes.from_fridge.R;
import com.recipes.from_fridge.databinding.SearchFragmentResultBinding;
import com.recipes.from_fridge.model.Ingredient;
import com.recipes.from_fridge.repository.RecipeRepository;
import com.recipes.from_fridge.service.RetrofitInstance;
import com.recipes.from_fridge.ui.search.SearchViewModel;
import com.recipes.from_fridge.ui.search.SearchViewModelFactory;
import com.recipes.from_fridge.ui.search.recipe_detail.RecipeDetailViewModel;
import com.recipes.from_fridge.ui.search.recipe_detail.RecipeDetailViewModelFactory;
import java.util.List;

public class SearchResultFragment extends Fragment {

    private SearchFragmentResultBinding binding;
    private SearchViewModel searchViewModel;
    private RecipeDetailViewModel recipeDetailViewModel;
    private SearchResultAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SearchFragmentResultBinding.inflate(inflater, container, false);

        RecipeRepository repository = new RecipeRepository(RetrofitInstance.getApiService());
        SearchViewModelFactory factory = new SearchViewModelFactory(repository);
        searchViewModel = new ViewModelProvider(requireActivity(), factory).get(SearchViewModel.class);

        RecipeDetailViewModelFactory detailFactory = new RecipeDetailViewModelFactory(repository);
        recipeDetailViewModel = new ViewModelProvider(requireActivity(), detailFactory)
                .get(RecipeDetailViewModel.class);

        setupRecyclerView();
        setupObservers();
        setupBackButton();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        adapter = new SearchResultAdapter(
                new java.util.ArrayList<>(),
                recipe -> {
                    recipeDetailViewModel.setRecipe(recipe);
                    NavHostFragment.findNavController(this)
                            .navigate(R.id.action_searchResult_to_recipeDetail);
                }
        );

        binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recipeRecyclerView.setAdapter(adapter);
    }

    private void setupObservers() {
        searchViewModel.getMatchedRecipes().observe(getViewLifecycleOwner(), recipeList -> {
            if (recipeList != null) {
                adapter.setItems(recipeList);
            }
        });

        searchViewModel.getSelectedIngredients().observe(getViewLifecycleOwner(), this::updateChips);
    }

    private void updateChips(List<Ingredient> ingredients) {
        binding.chipGroup.removeAllViews();
        for (Ingredient ingredient : ingredients) {
            Chip chip = new Chip(requireContext());
            chip.setText(ingredient.getName());
            chip.setClickable(false);
            chip.setCloseIconVisible(false);
            binding.chipGroup.addView(chip);
        }
    }

    private void setupBackButton() {
        binding.btnBack.setOnClickListener(v -> NavHostFragment.findNavController(this).navigateUp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
