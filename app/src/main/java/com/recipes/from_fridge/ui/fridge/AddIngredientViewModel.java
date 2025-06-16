package com.recipes.from_fridge.ui.fridge;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.recipes.from_fridge.model.FridgeIngredient;
import com.recipes.from_fridge.model.Ingredient;
import com.recipes.from_fridge.service.ApiService;
import com.recipes.from_fridge.service.RetrofitInstance;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddIngredientViewModel extends ViewModel {

    private final MutableLiveData<List<Ingredient>> searchResults = new MutableLiveData<>();
    private final ApiService apiService = RetrofitInstance.getApiService();

    public LiveData<List<Ingredient>> getSearchResults() {
        return searchResults;
    }


    public void searchIngredients(String query) {
        apiService.searchIngredients(query).enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    searchResults.setValue(response.body());
                } else {
                    searchResults.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
                searchResults.setValue(new ArrayList<>());
            }
        });
    }

    public void addIngredientToFridge(Ingredient ingredient, AddIngredientFragment.AddCallback callback) {
        apiService.addIngredientToFridge(ingredient.getName()).enqueue(new Callback<FridgeIngredient>() {
            @Override
            public void onResponse(Call<FridgeIngredient> call, Response<FridgeIngredient> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String name = response.body().getIngredient().getName();
                    callback.onSuccess("Added " + name + " to your fridge.");
                } else {
                    callback.onFailure("Add failed. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FridgeIngredient> call, Throwable t) {
                callback.onFailure("Add failed: " + t.getMessage());
            }
        });
    }



}
