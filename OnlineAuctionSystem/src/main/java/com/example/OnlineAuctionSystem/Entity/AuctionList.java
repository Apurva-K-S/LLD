package com.example.OnlineAuctionSystem.Entity;

import com.example.OnlineAuctionSystem.Enum.AuctionListStatus;

import java.time.LocalDateTime;

public class AuctionList {
    private String id;
    private String itemId;
    private double basePrice;
    private LocalDateTime startTime;
    private int duration;
    private LocalDateTime endTime;
    private AuctionListStatus auctionListStatus;
    private double highestBidValue;
    private String userIdOfHighestBidValue;

    public AuctionList(String id, String itemId, double basePrice, LocalDateTime startTime, int duration, LocalDateTime endTime, AuctionListStatus auctionListStatus, double highestBidValue, String userIdOfHighestBidValue) {
        this.id = id;
        this.itemId = itemId;
        this.basePrice = basePrice;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = endTime;
        this.auctionListStatus = auctionListStatus;
        this.highestBidValue = highestBidValue;
        this.userIdOfHighestBidValue = userIdOfHighestBidValue;
    }

    public String getUserIdOfHighestBidValue() {
        return userIdOfHighestBidValue;
    }

    public void setUserIdOfHighestBidValue(String userIdOfHighestBidValue) {
        this.userIdOfHighestBidValue = userIdOfHighestBidValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public AuctionListStatus getAuctionListStatus() {
        return auctionListStatus;
    }

    public void setAuctionListStatus(AuctionListStatus auctionListStatus) {
        this.auctionListStatus = auctionListStatus;
    }

    public double getHighestBidValue() {
        return highestBidValue;
    }

    public void setHighestBidValue(double highestBidValue) {
        this.highestBidValue = highestBidValue;
    }

    @Override
    public String toString() {
        return "AuctionList{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", basePrice=" + basePrice +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", endTime=" + endTime +
                ", auctionListStatus=" + auctionListStatus +
                ", highestBidValue=" + highestBidValue +
                ", userIdOfHighestBidValue='" + userIdOfHighestBidValue + '\'' +
                '}';
    }
}
