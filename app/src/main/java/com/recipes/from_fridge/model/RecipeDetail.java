package com.recipes.from_fridge.model;

import java.util.List;

public class RecipeDetail {
    private String title;
    private String imageUrl;
    private Integer servings;
    private String difficulty;
    private Integer cookTime;
    private List<String> method;
    private String description;
    private List<IngredientDetail> ingredients;
    private int matchedCount;

    public RecipeDetail() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Integer getServings() { return servings; }
    public void setServings(Integer servings) { this.servings = servings; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Integer getCookTime() { return cookTime; }
    public void setCookTime(Integer cookTime) { this.cookTime = cookTime; }

    public List<String> getMethod() { return method; }
    public void setMethod(List<String> method) { this.method = method; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<IngredientDetail> getIngredients() { return ingredients; }
    public void setIngredients(List<IngredientDetail> ingredients) { this.ingredients = ingredients; }

    public int getMatchedCount() { return matchedCount; }
    public void setMatchedCount(int matchedCount) { this.matchedCount = matchedCount; }
}

