package com.recipes.from_fridge.model;

public class IngredientDetail {
    private String name;
    private String quantity;
    private String preparation;
    private boolean owned;

    public IngredientDetail() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getPreparation() { return preparation; }
    public void setPreparation(String preparation) { this.preparation = preparation; }

    public boolean isOwned() { return owned; }
    public void setOwned(boolean owned) { this.owned = owned; }
}

