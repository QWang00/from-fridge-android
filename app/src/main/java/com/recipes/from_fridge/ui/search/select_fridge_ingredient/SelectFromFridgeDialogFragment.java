package com.recipes.from_fridge.ui.search.select_fridge_ingredient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.recipes.from_fridge.databinding.SearchDialogSelectFridgeBinding;
import com.recipes.from_fridge.model.FridgeIngredient;
import com.recipes.from_fridge.model.Ingredient;
import com.recipes.from_fridge.model.SelectableFridgeIngredient;
import com.recipes.from_fridge.ui.fridge.FridgeViewModel;
import com.recipes.from_fridge.ui.search.SearchViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * DialogFragment that displays fridge ingredients with checkboxes,
 * allowing users to select ingredients for recipe search.
 */
public class SelectFromFridgeDialogFragment extends DialogFragment {

    private SearchDialogSelectFridgeBinding binding;
    private FridgeViewModel fridgeViewModel;
    private SearchViewModel searchViewModel;
    private FridgeIngredientCheckboxAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SearchDialogSelectFridgeBinding.inflate(inflater, container, false);

        // 获取共享的 ViewModel
        fridgeViewModel = new ViewModelProvider(requireActivity()).get(FridgeViewModel.class);
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        setupRecyclerView();
        setupButtons();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        adapter = new FridgeIngredientCheckboxAdapter();
        binding.fridgeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.fridgeRecyclerView.setAdapter(adapter);

        // 观察 Fridge 数据并转为 checkbox 列表
        fridgeViewModel.getFridgeIngredients().observe(getViewLifecycleOwner(), list -> {
            if (list != null) {
                adapter.setItems(convertToSelectable(list));
            }
        });

        fridgeViewModel.loadFridgeIngredients(); // 触发加载
    }

    private void setupButtons() {
        binding.buttonOk.setOnClickListener(v -> {
            List<SelectableFridgeIngredient> selectedItems = adapter.getItems();
            for (SelectableFridgeIngredient item : selectedItems) {
                if (item.isSelected()) {
                    Ingredient ingredient = item.getFridgeIngredient().getIngredient();
                    searchViewModel.addIngredient(ingredient);
                }
            }
            dismiss();
        });

        binding.buttonCancel.setOnClickListener(v -> dismiss());
    }

    private List<SelectableFridgeIngredient> convertToSelectable(List<FridgeIngredient> rawList) {
        List<SelectableFridgeIngredient> result = new ArrayList<>();
        for (FridgeIngredient fi : rawList) {
            result.add(new SelectableFridgeIngredient(fi));
        }
        return result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
