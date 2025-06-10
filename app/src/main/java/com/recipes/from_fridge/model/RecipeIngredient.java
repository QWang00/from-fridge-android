package com.recipes.from_fridge.model;

public class RecipeIngredient {
    private Integer id;
    private Ingredient ingredient;
    private String quantity;
    private String preparation;

    public RecipeIngredient() {}

    public RecipeIngredient(Integer id, Ingredient ingredient, String quantity, String preparation) {
        this.id = id;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.preparation = preparation;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Ingredient getIngredient() { return ingredient; }
    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getPreparation() { return preparation; }
    public void setPreparation(String preparation) { this.preparation = preparation; }
}
