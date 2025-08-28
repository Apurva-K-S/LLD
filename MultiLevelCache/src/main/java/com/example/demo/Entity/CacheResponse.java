package com.example.demo.Entity;

public class CacheResponse {
    private String key;
    private String value;
    private long timeTakenInMs;
    private boolean found;
    private int foundLevel;

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public CacheResponse(String key, String value, long timeTakenInMs, int foundLevel, boolean found) {
        this.key = key;
        this.value = value;
        this.timeTakenInMs = timeTakenInMs;
        this.foundLevel = foundLevel;
        this.found= found;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTimeTakenInMs() {
        return timeTakenInMs;
    }

    public void setTimeTakenInMs(long timeTakenInMs) {
        this.timeTakenInMs = timeTakenInMs;
    }

    public int getFoundLevel() {
        return foundLevel;
    }

    public void setFoundLevel(int foundLevel) {
        this.foundLevel = foundLevel;
    }
}
