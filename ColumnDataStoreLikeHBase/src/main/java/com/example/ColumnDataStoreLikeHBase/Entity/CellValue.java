package com.example.ColumnDataStoreLikeHBase.Entity;

public class CellValue {
    private final String value;
    private final long timestamp;  // Can be system time or user provided

    public CellValue(String value, long timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }
}