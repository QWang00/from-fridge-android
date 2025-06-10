package com.recipes.from_fridge.model;

public class FridgeIngredient {
    private Integer id;
    private Ingredient ingredient;

    public FridgeIngredient() {}

    public FridgeIngredient(Integer id, Ingredient ingredient) {
        this.id = id;
        this.ingredient = ingredient;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Ingredient getIngredient() { return ingredient; }
    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }
}

