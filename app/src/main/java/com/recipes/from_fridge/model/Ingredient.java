package com.recipes.from_fridge.model;

public class Ingredient {
    private Integer id;
    private String name;

    public Ingredient() {}

    public Ingredient(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

