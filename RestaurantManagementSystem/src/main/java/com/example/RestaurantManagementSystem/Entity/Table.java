package com.example.RestaurantManagementSystem.Entity;

import com.example.RestaurantManagementSystem.Enum.TableStatus;

public class Table {
    private Integer id;
    private Integer capacity;
    private TableStatus tableStatus;

    public Table(Integer id, Integer capacity, TableStatus tableStatus) {
        this.id = id;
        this.capacity = capacity;
        this.tableStatus = tableStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", tableStatus=" + tableStatus +
                '}';
    }
}
