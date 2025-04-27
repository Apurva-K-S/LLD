package com.example.Elevator;

import java.util.Collections;
import java.util.PriorityQueue;

public class ElevatorCar {
    Door door;
    int currentFloor;
    ElevatorStatus elevatorStatus;
    InternalPanel internalPanel;
    Direction currentDirection;
    int capacity;
    int currentCapacity;
    PriorityQueue<Integer> upRequests;
    PriorityQueue<Integer> downRequests;

    public ElevatorCar(int capacity, int currentFloor) {
        this.door=new Door(false);
        this.capacity = capacity;
        this.currentFloor = currentFloor;
        this.elevatorStatus = ElevatorStatus.STOPPED;
        this.internalPanel = new InternalPanel();
        this.currentDirection = Direction.UP;
        this.currentCapacity = 0;
        upRequests = new PriorityQueue<>();
        downRequests = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addRequest(Request request) {
        if(currentFloor<request.destinationFloor && request.direction == Direction.UP) {
            upRequests.add(request.destinationFloor);
        }
        if(currentFloor > request.destinationFloor && request.direction == Direction.DOWN) {
            downRequests.add(request.destinationFloor);
        }
    }

    public void operate() {
        while(!upRequests.isEmpty() || !downRequests.isEmpty()) {
            if(currentDirection == Direction.UP) {
                while (!upRequests.isEmpty()) {
                    int nextFloor = upRequests.poll();
                    moveToFloor(nextFloor);
                }
                currentDirection = Direction.DOWN;
            }
            if(currentDirection == Direction.DOWN) {
                while(!downRequests.isEmpty())
                {
                    int nextFloor = downRequests.poll();
                    moveToFloor(nextFloor);
                }
                currentDirection = Direction.UP;
            }
        }
        elevatorStatus = ElevatorStatus.STOPPED;
        System.out.println("Elevator has stopped at floor " + currentFloor);
    }

    public void moveToFloor(int nextFloor) {
        currentFloor = nextFloor;
        System.out.println("Going to floor " + nextFloor);
        door.open();
        door.close();
    }
}
