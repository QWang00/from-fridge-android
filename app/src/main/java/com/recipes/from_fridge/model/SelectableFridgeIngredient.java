package com.recipes.from_fridge.model;

public class SelectableFridgeIngredient {
    private FridgeIngredient fridgeIngredient;
    private boolean selected;

    public SelectableFridgeIngredient(FridgeIngredient fridgeIngredient) {
        this.fridgeIngredient = fridgeIngredient;
        this.selected = false;
    }

    public FridgeIngredient getFridgeIngredient() {
        return fridgeIngredient;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
