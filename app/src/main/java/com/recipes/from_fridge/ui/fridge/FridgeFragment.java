package com.recipes.from_fridge.ui.fridge;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
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
                },
                FridgeAdapter.ActionMode.REMOVE
        );

        binding.myFridgeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.setAdapter(adapter);
    }

    private void observeViewModel(){
        viewModel.getFridgeIngredients().observe(getViewLifecycleOwner(), adapter::setFridgeIngredients);
    }

    private void setupFab() {
        binding.fabAddIngredient.setOnClickListener(v -> {
            // TODO: 跳转到添加页面
            // 如果你用 Navigation Component:
            // NavHostFragment.findNavController(this).navigate(R.id.action_fridgeFragment_to_searchFragment);
        });
    }



}