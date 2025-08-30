package com.example.ColumnDataStoreLikeHBase.Entity;

import java.util.Objects;

// Represents a Column Qualifier, dynamic per row
public class ColumnQualifier {
    private final String name;

    public ColumnQualifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColumnQualifier)) return false;
        return name.equals(((ColumnQualifier) o).getName());
    }
    @Override
    public int hashCode() { return name.hashCode(); }
}