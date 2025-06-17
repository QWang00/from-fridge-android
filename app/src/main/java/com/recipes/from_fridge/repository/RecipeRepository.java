package com.recipes.from_fridge.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.recipes.from_fridge.service.ApiService;
import com.recipes.from_fridge.model.RecipeDetail;
import com.recipes.from_fridge.model.RecipePreview;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {
    private final ApiService apiService;

    public RecipeRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<List<RecipePreview>> searchRecipesByIngredients(List<String> ingredientNames) {
        MutableLiveData<List<RecipePreview>> liveData = new MutableLiveData<>();

        apiService.searchRecipesByIngredients(ingredientNames).enqueue(new Callback<List<RecipePreview>>() {
            @Override
            public void onResponse(Call<List<RecipePreview>> call, Response<List<RecipePreview>> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else {
                    liveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<RecipePreview>> call, Throwable t) {
                liveData.setValue(null);
            }
        });

        return liveData;
    }

    public LiveData<RecipeDetail> getRecipeDetail(int recipeId, List<String> matchedIngredients) {
        MutableLiveData<RecipeDetail> liveData = new MutableLiveData<>();

        apiService.getRecipeDetail(recipeId, matchedIngredients).enqueue(new Callback<RecipeDetail>() {
            @Override
            public void onResponse(Call<RecipeDetail> call, Response<RecipeDetail> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else {
                    liveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<RecipeDetail> call, Throwable t) {
                liveData.setValue(null);
            }
        });

        return liveData;
    }

}

