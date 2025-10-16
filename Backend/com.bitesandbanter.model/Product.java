/**
 * Defines a single Menu Item.
 * Fields correspond to menu item details in the original HTML.
 */

package com.bitesandbanter.model;

import java.util.Map;

public class Product {
    private String id;
    private String name;
    private double price;
    private String description;
    // Map of Ingredient Name -> Quantity needed per item (used for inventory tracking)
    private Map<String, Integer> ingredients; 

    public Product(String id, String name, double price, String description, Map<String, Integer> ingredients) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.ingredients = ingredients;
    }
    // Getters and Setters (essential for JavaFX data binding)
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public Map<String, Integer> getIngredients() { return ingredients; }
}
