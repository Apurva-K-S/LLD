package com.example.ColumnDataStoreLikeHBase.Entity;

public class RowKey {
    private final String key;

    public RowKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    // override equals and hashCode for map keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RowKey)) return false;
        RowKey rowKey = (RowKey) o;
        return key.equals(rowKey.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}