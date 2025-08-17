package com.example.OnlineStockBrokerage.Service;

import com.example.OnlineStockBrokerage.Entities.Company;

import java.util.Map;

public interface MarketDataService {
    double getQuote(String symbol);
    void updateQuote(String symbol, double newPrice);
    Company getCompanyData(String symbol);
    Map<String, Double> getAllQuotes();
    Map<String, String> getAllNames();
}
