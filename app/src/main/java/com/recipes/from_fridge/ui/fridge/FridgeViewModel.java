package com.recipes.from_fridge.ui.fridge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.recipes.from_fridge.repository.FridgeIngredientRepository;
import com.recipes.from_fridge.model.FridgeIngredient;
import com.recipes.from_fridge.model.Ingredient;
import com.recipes.from_fridge.service.RetrofitInstance;
import java.util.List;

public class FridgeViewModel extends ViewModel {

    private final FridgeIngredientRepository repository = new FridgeIngredientRepository(RetrofitInstance.getApiService());;

    private final MutableLiveData<List<FridgeIngredient>> fridgeIngredients = new MutableLiveData<>();

    public FridgeViewModel() {
        loadFridgeIngredients();
    }

    public LiveData<List<FridgeIngredient>> getFridgeIngredients() {
        return fridgeIngredients;
    }

    public void loadFridgeIngredients() {
        repository.getFridgeIngredients().observeForever(fetched -> {
            fridgeIngredients.setValue(fetched);
        });
    }

    public void removeIngredientFromFridge(FridgeIngredient fridgeIngredient) {
        repository.removeIngredientFromFridge(
                fridgeIngredient.getId(),
                this::loadFridgeIngredients,
                () -> {}
        );
    }

    public void addIngredientToFridge(String name) {
        repository.addToFridge(
                name,
                this::loadFridgeIngredients,
                () -> {}
        );
    }

    public void clearFridge() {
        repository.clearFridge(
                this::loadFridgeIngredients,
                () -> {}
        );
    }
}
