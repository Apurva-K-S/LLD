package com.example.OnlineStockBrokerage.Service;

import com.example.OnlineStockBrokerage.Entities.Company;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MarketDataServiceImpl implements MarketDataService{
    private final Map<String, Company> companyStore;

    public MarketDataServiceImpl() {
        this.companyStore = new HashMap<>();
    }

    @Override
    public double getQuote(String companyId) {
        Company company = companyStore.get(companyId);
        if (company == null) throw new IllegalArgumentException("Company not found");
        return company.getCurrentPrice();
    }

    @Override
    public void updateQuote(String companyId, double newPrice) {
        Company company = companyStore.get(companyId);
        if (company == null) throw new IllegalArgumentException("Company not found");
        company.setCurrentPrice(newPrice);
        company.setPriceUpdatedAt(LocalDateTime.now());
    }

    @Override
    public Company getCompanyData(String companyId) {
        return companyStore.get(companyId);
    }

    public Company addCompany(String name, double initialPrice, double marketCap) {
        UUID uuid = UUID.randomUUID();

        // Convert UUID to string
        String uuidString = uuid.toString();

        Company c = new Company();
        c.setId(uuidString);
        c.setName(name);
        c.setMarketCap(marketCap);
        c.setCurrentPrice(initialPrice);
        c.setPriceUpdatedAt(LocalDateTime.now());
        c.setCreatedAt(LocalDateTime.now());
        companyStore.put(uuidString, c);
        System.out.println("Added company: " + c);
        return c;
    }

    @Override
    public Map<String, Double> getAllQuotes() {
        Map<String, Double> quotes = new HashMap<>();
        for (Company c : companyStore.values()) {
            quotes.put(c.getId(), c.getCurrentPrice());
        }
        return quotes;
    }

    @Override
    public Map<String, String> getAllNames() {
        Map<String, String> names = new HashMap<>();
        for (Company c : companyStore.values()) {
            names.put(c.getId(), c.getName());
        }
        return names;
    }

}
