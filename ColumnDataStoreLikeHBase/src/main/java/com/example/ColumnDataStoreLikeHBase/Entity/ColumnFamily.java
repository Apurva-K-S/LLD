package com.example.ColumnDataStoreLikeHBase.Entity;

// Represents a Column Family, fixed set defined upfront
public class ColumnFamily {
    private final String name;

    public ColumnFamily(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColumnFamily)) return false;
        return name.equals(((ColumnFamily) o).getName());
    }

    @Override
    public int hashCode() { return name.hashCode(); }
}
