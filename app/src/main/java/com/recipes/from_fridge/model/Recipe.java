package com.recipes.from_fridge.model;

import java.util.List;

public class Recipe {
    private Integer id;
    private String title;
    private String imageUrl;
    private Integer servings;
    private String difficulty;
    private Integer cookTime;
    private List<String> method;
    private String description;
    private List<RecipeIngredient> ingredients;

    public Recipe() {}

    public Recipe(Integer id, String title, String imageUrl, Integer servings, String difficulty,
                  Integer cookTime, List<String> method, String description,
                  List<RecipeIngredient> ingredients) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.servings = servings;
        this.difficulty = difficulty;
        this.cookTime = cookTime;
        this.method = method;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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

    public List<RecipeIngredient> getIngredients() { return ingredients; }
    public void setIngredients(List<RecipeIngredient> ingredients) { this.ingredients = ingredients; }
}
