package com.recipes.from_fridge.ui.search;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.chip.Chip;
import com.recipes.from_fridge.R;
import com.recipes.from_fridge.databinding.SearchFragmentBinding;
import com.recipes.from_fridge.model.Ingredient;
import com.recipes.from_fridge.ui.search.select_fridge_ingredient.SelectFromFridgeDialogFragment;

import java.util.List;

/**
 * Main entry fragment for recipe search.
 * Lets users select ingredients and match recipes.
 */
public class SearchFragment extends Fragment {

    private SearchFragmentBinding binding;
    private SearchViewModel searchViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SearchFragmentBinding.inflate(inflater, container, false);
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        setupListeners();
        observeSelectedIngredients();

        return binding.getRoot();
    }

    private void setupListeners() {

        binding.fromFridgeButton.setOnClickListener(v -> {
            SelectFromFridgeDialogFragment dialog = new SelectFromFridgeDialogFragment();
            dialog.show(getParentFragmentManager(), "selectFridge");
        });

        binding.searchMoreButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_search_to_selectIngredient);
        });


        binding.matchButton.setOnClickListener(v -> {
            searchViewModel.searchRecipes();
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_search_to_searchResult);
        });
    }

    private void observeSelectedIngredients() {
        searchViewModel.getSelectedIngredients().observe(getViewLifecycleOwner(), this::updateChips);
    }

    private void updateChips(List<Ingredient> ingredientList) {
        binding.chipGroup.removeAllViews();

        for (Ingredient ingredient : ingredientList) {
            Chip chip = new Chip(requireContext());
            chip.setText(ingredient.getName());
            chip.setCloseIconVisible(true);
            chip.setClickable(false);
            chip.setOnCloseIconClickListener(v -> {
                searchViewModel.removeIngredient(ingredient);
            });

            binding.chipGroup.addView(chip);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
