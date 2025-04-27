package com.example.Elevator;

public class Request {
    int destinationFloor;
    Direction direction;

    public Request(int destinationFloor, Direction direction) {
        this.destinationFloor = destinationFloor;
        this.direction = direction;
    }
}
