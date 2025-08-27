package com.example.OnlineAuctionSystem.Entity;

import com.example.OnlineAuctionSystem.Enum.Category;

public class SearchCriteria {
    private String itemName;
    private Category category;
    private double minPrice;
    private double maxPrice;

    public SearchCriteria(String itemName, Category category, double minPrice, double maxPrice) {
        this.itemName = itemName;
        this.category = category;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "itemName='" + itemName + '\'' +
                ", category=" + category +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
