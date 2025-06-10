package com.recipes.from_fridge.service;

import com.recipes.from_fridge.model.FridgeIngredient;
import com.recipes.from_fridge.model.RecipeDetail;
import com.recipes.from_fridge.model.RecipePreview;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    @GET("fridge/ingredients")
    Call<List<FridgeIngredient>> getAllFridgeIngredients();

    @POST("fridge/ingredient")
    @FormUrlEncoded
    Call<FridgeIngredient> addIngredientToFridge(@Field("ingredient") String ingredientName);

    @DELETE("fridge/ingredient/{id}")
    Call<String> removeIngredientFromFridge(@Path("id") Integer id);

    @DELETE("fridge/ingredients")
    Call<String> clearFridge();


    @GET("recipes/search")
    Call<List<RecipePreview>> searchRecipesByIngredients(
            @Query("ingredientNames") List<String> ingredientNames
    );

    @GET("recipes/{id}/detail")
    Call<RecipeDetail> getRecipeDetail(
            @Path("id") Integer recipeId,
            @Query("matchedIngredients") List<String> matchedIngredients
    );
}
