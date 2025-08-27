package com.example.OnlineAuctionSystem.Entity;

import java.time.LocalDateTime;

public class Bid {
    private String id;
    private String userId;
    private String auctionListId;
    private LocalDateTime createdAt;
    private double bidAmount;

    public Bid(String id, String userId, String auctionListId, LocalDateTime createdAt, double bidAmount) {
        this.id = id;
        this.userId = userId;
        this.auctionListId = auctionListId;
        this.createdAt = createdAt;
        this.bidAmount = bidAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuctionListId() {
        return auctionListId;
    }

    public void setAuctionListId(String auctionListId) {
        this.auctionListId = auctionListId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", auctionListId='" + auctionListId + '\'' +
                ", createdAt=" + createdAt +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
