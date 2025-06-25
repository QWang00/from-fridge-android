package com.recipes.from_fridge.model;

import java.util.List;

public class RecipePreview {
    private Integer id;
    private String title;
    private String imageUrl;
    private int matchedCount;
    private List<String> matchedIngredients;

    public RecipePreview() {}

    public Integer getId(){return id;};
    public void setId(){this.id = id;}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getMatchedCount() { return matchedCount; }
    public void setMatchedCount(int matchedCount) { this.matchedCount = matchedCount; }

    public List<String> getMatchedIngredients() { return matchedIngredients; }
    public void setMatchedIngredients(List<String> matchedIngredients) { this.matchedIngredients = matchedIngredients; }
}

