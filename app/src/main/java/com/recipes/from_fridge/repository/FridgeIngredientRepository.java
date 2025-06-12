package com.recipes.from_fridge.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.recipes.from_fridge.model.FridgeIngredient;
import com.recipes.from_fridge.model.Ingredient;
import com.recipes.from_fridge.service.ApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FridgeIngredientRepository {

    private final ApiService apiService;

    public FridgeIngredientRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<List<FridgeIngredient>> getFridgeIngredients() {
        MutableLiveData<List<FridgeIngredient>> data = new MutableLiveData<>();

        apiService.getAllFridgeIngredients().enqueue(new Callback<List<FridgeIngredient>>() {
            @Override
            public void onResponse(Call<List<FridgeIngredient>> call, Response<List<FridgeIngredient>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<FridgeIngredient>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public void removeIngredientFromFridge(int ingredientId, Runnable onSuccess, Runnable onError) {
        apiService.removeIngredientFromFridge(ingredientId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    onSuccess.run();
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void addToFridge(String name, Runnable onSuccess, Runnable onError) {
        apiService.addIngredientToFridge(name).enqueue(new Callback<FridgeIngredient>() {
            @Override
            public void onResponse(Call<FridgeIngredient> call, Response<FridgeIngredient> response) {
                if (response.isSuccessful()) {
                    onSuccess.run();
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<FridgeIngredient> call, Throwable t) {
                onError.run();
            }
        });
    }

    public void clearFridge(Runnable onSuccess, Runnable onError) {
        apiService.clearFridge().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    onSuccess.run();
                } else {
                    onError.run();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onError.run();
            }
        });
    }

}
