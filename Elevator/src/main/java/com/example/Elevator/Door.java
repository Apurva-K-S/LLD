package com.example.Elevator;

public class Door {
    boolean isOpen;

    public Door(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void open(){
        System.out.println("opening door");
    }
    public void close(){
        System.out.println("closing door");
    }
}
