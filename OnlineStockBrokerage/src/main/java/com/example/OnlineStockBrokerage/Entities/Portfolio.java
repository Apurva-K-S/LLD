package com.example.OnlineStockBrokerage.Entities;

public class Portfolio {
    private String companyId;
    private String companyName;
    private int sharesOwned;
    private double avgBuyPrice;
    private double currentPrice;
    private double unrealizedPL;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getSharesOwned() {
        return sharesOwned;
    }

    public void setSharesOwned(int sharesOwned) {
        this.sharesOwned = sharesOwned;
    }

    public double getAvgBuyPrice() {
        return avgBuyPrice;
    }

    public void setAvgBuyPrice(double avgBuyPrice) {
        this.avgBuyPrice = avgBuyPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getUnrealizedPL() {
        return unrealizedPL;
    }

    public void setUnrealizedPL(double unrealizedPL) {
        this.unrealizedPL = unrealizedPL;
    }

    public String toString() {
        return this.companyId + " " + this.companyName + " " + this.avgBuyPrice + " " + this.currentPrice + " " + this.sharesOwned + " " + this.unrealizedPL;
    }
}
