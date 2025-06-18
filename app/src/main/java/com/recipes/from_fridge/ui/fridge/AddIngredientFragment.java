package com.recipes.from_fridge.ui.fridge;

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
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.recipes.from_fridge.databinding.FragmentFridgeAddBinding;

public class AddIngredientFragment extends Fragment {
    private FragmentFridgeAddBinding binding;
    private AddIngredientViewModel viewModel;
    private SearchIngredientAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFridgeAddBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AddIngredientViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.btnBack.setOnClickListener(v ->{
            NavHostFragment.findNavController(this).navigateUp();
        });
        setupRecyclerView();
        setupSearchBox();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        adapter = new SearchIngredientAdapter(ingredient -> {
        viewModel.addIngredientToFridge(ingredient, new AddCallback() {
            @Override
            public void onSuccess(String message) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(requireContext(),error, Toast.LENGTH_SHORT).show();

            }
        });
        });

        binding.searchIngredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.searchIngredientRecyclerView.setAdapter(adapter);

        viewModel.getSearchResults().observe(getViewLifecycleOwner(), adapter::setIngredients);
    }

    public interface AddCallback{
        void onSuccess(String message);
        void onFailure(String error);
    }

    private void setupSearchBox() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.searchIngredients(s.toString());
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }
}
