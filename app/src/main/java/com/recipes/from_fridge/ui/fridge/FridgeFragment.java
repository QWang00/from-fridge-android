package com.recipes.from_fridge.ui.fridge;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.recipes.from_fridge.R;
import com.recipes.from_fridge.databinding.FragmentFridgeBinding;

public class FridgeFragment extends Fragment {
    private FridgeViewModel viewModel;
    private FragmentFridgeBinding binding;
    private FridgeAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       viewModel =
                new ViewModelProvider(this).get(FridgeViewModel.class);

        binding = FragmentFridgeBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        setupRemoveIngredientRecyclerView();
        setupFab();
        setupClearButton();
        observeViewModel();
        viewModel.loadFridgeIngredients();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupRemoveIngredientRecyclerView(){
        adapter = new FridgeAdapter(
                fridgeIngredient -> {
                    new AlertDialog.Builder(requireContext())
                            .setTitle("Remove Fridge Ingredient")
                            .setMessage("Are you sure you want to remove \"" + fridgeIngredient.getIngredient().getName() + "\" from your fridge?")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                viewModel.removeIngredientFromFridge(fridgeIngredient);
                            })
                            .setNegativeButton("Cancel", (dialog, which) -> {
                                dialog.dismiss();
                            })
                            .show();
                }
        );

        binding.myFridgeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setAdapter(adapter);
    }

    private void observeViewModel(){
        viewModel.getFridgeIngredients().observe(getViewLifecycleOwner(), ingredients -> {
            adapter.setFridgeIngredients(ingredients);

            if (ingredients == null || ingredients.isEmpty()) {
                binding.tvEmptyMessage.setVisibility(View.VISIBLE);
            } else {
                binding.tvEmptyMessage.setVisibility(View.GONE);
            }
        });
    }

    private void setupFab() {
        binding.fabAddIngredient.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(FridgeFragment.this);
            navController.navigate(R.id.navigation_add_ingredient);
        });
    }

    private void setupClearButton() {
        binding.btnClearFridge.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Clear Fridge")
                    .setMessage("Are you sure you want to remove all items from your fridge?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        viewModel.clearFridge();
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }




}