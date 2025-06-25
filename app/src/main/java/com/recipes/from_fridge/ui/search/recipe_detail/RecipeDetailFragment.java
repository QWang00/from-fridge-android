package com.recipes.from_fridge.ui.search.recipe_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.recipes.from_fridge.databinding.SearchFragmentRecipeDetailBinding;
import com.recipes.from_fridge.model.RecipeDetail;
import com.recipes.from_fridge.repository.RecipeRepository;
import com.recipes.from_fridge.service.RetrofitInstance;

public class RecipeDetailFragment extends androidx.fragment.app.Fragment {

    private SearchFragmentRecipeDetailBinding binding;
    private RecipeDetailViewModel viewModel;
    private IngredientAdapter ingredientAdapter;
    private StepsAdapter StepsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SearchFragmentRecipeDetailBinding.inflate(inflater, container, false);

        RecipeRepository repo = new RecipeRepository(RetrofitInstance.getApiService());
        RecipeDetailViewModelFactory factory = new RecipeDetailViewModelFactory(repo);
        viewModel = new ViewModelProvider(requireActivity(), factory)
                .get(RecipeDetailViewModel.class);

        setupRecyclerViews();
        setupObservers();
        setupBackButton();

        return binding.getRoot();
    }

    private void setupRecyclerViews() {
        ingredientAdapter = new IngredientAdapter();
        binding.ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.ingredientRecyclerView.setAdapter(ingredientAdapter);

        StepsAdapter = new StepsAdapter();
        binding.stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.stepsRecyclerView.setAdapter(StepsAdapter);
    }

    private void setupObservers() {
        viewModel.getRecipeDetail().observe(getViewLifecycleOwner(), this::bindRecipeDetail);
    }

    private void bindRecipeDetail(RecipeDetail detail) {
        if (detail == null) return;

        binding.textTitle.setText(detail.getTitle());

        Glide.with(this)
                .load(detail.getImageUrl())
                .into(binding.imageRecipe);

        ingredientAdapter.setItems(detail.getIngredients());
        StepsAdapter.setItems(detail.getMethod());
    }

    private void setupBackButton() {
        binding.btnBack.setOnClickListener(v ->
                requireActivity().onBackPressed()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
