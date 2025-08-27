package com.example.RestaurantManagementSystem.Service;

import com.example.RestaurantManagementSystem.Entity.Ingredient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryService {

    private Map<String, Ingredient> ingredientMap = new ConcurrentHashMap<>();
    private final double LOW_STOCK_THRESHOLD = 10.0; // Example threshold

    // Create a new ingredient and add to inventory
    public Ingredient createIngredient(String id, String name, double initialQuantity) {
        if (id == null || name == null || initialQuantity < 0) {
            throw new IllegalArgumentException("Invalid ingredient parameters");
        }
        if (ingredientMap.containsKey(id)) {
            throw new IllegalArgumentException("Ingredient with ID already exists");
        }
        Ingredient ingredient = new Ingredient(id, name, initialQuantity);
        ingredientMap.put(id, ingredient);
        return ingredient;
    }

    // Add or update an existing ingredient quantity
    public void addOrUpdateIngredient(String id, String name, double quantity) {
        if (id == null || name == null || quantity < 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        ingredientMap.compute(id, (key, existingIngredient) -> {
            if (existingIngredient == null) {
                return new Ingredient(id, name, quantity);
            } else {
                existingIngredient.setQuantityAvailable(quantity);
                existingIngredient.setName(name);
                return existingIngredient;
            }
        });
    }

    // Get ingredient by id
    public Ingredient getIngredientById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return ingredientMap.get(id);
    }

    // Deduct quantity for ingredient when used in order or kitchen
    public boolean deductIngredientQuantity(String ingredientId, double quantity) {
        Ingredient ingredient = getIngredientById(ingredientId);
        if (ingredient == null || quantity <= 0) {
            return false;
        }
        double currentQty = ingredient.getQuantityAvailable();
        if (currentQty < quantity) {
            return false; // Not enough quantity
        }
        ingredient.setQuantityAvailable(currentQty - quantity);
        return true;
    }

    // Check if ingredient stock is below threshold
    public boolean isLowStock(String ingredientId) {
        Ingredient ingredient = getIngredientById(ingredientId);
        return ingredient != null && ingredient.getQuantityAvailable() <= LOW_STOCK_THRESHOLD;
    }

    // List all ingredients
    public Map<String, Ingredient> getAllIngredients() {
        return ingredientMap;
    }
}
