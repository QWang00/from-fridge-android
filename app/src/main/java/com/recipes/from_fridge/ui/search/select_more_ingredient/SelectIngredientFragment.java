package com.recipes.from_fridge.ui.search.select_more_ingredient;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.recipes.from_fridge.databinding.SearchFragmentMoreIngredientBinding;
import com.recipes.from_fridge.model.Ingredient;
import com.recipes.from_fridge.ui.fridge.AddIngredientViewModel;
import com.recipes.from_fridge.ui.search.SearchViewModel;

public class SelectIngredientFragment extends Fragment implements IngredientSelectAdapter.OnAddClickListener {

    private SearchFragmentMoreIngredientBinding binding;
    private AddIngredientViewModel addViewModel;
    private SearchViewModel searchViewModel;
    private IngredientSelectAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SearchFragmentMoreIngredientBinding.inflate(inflater, container, false);

        // 用于搜索 Ingredient 列表
        addViewModel = new ViewModelProvider(this).get(AddIngredientViewModel.class);

        // 用于保存 selectedIngredients（主流程共享）
        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);

        setupRecyclerView();
        setupSearchListener();
        setupBackButton();
        observeSearchResults();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        adapter = new IngredientSelectAdapter(this);
        binding.selectIngredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.selectIngredientRecyclerView.setAdapter(adapter);
    }

    private void setupSearchListener() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addViewModel.searchIngredients(s.toString());
            }
        });
    }

    private void setupBackButton() {
        binding.btnBack.setOnClickListener(v -> NavHostFragment.findNavController(this).navigateUp());
    }

    private void observeSearchResults() {
        addViewModel.getSearchResults().observe(getViewLifecycleOwner(), ingredients -> {
            adapter.setIngredients(ingredients);
        });
    }

    @Override
    public void onAddClick(Ingredient ingredient) {
        searchViewModel.addIngredient(ingredient);  // ✅ 添加到 selectedIngredients
        Toast.makeText(getContext(), "Added: " + ingredient.getName(), Toast.LENGTH_SHORT).show();
        NavHostFragment.findNavController(this).navigateUp();  // ✅ 返回 SearchFragment
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
