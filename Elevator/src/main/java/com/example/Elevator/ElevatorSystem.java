package com.example.Elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    List<ElevatorCar> elevators;
    Scheduler scheduler;
    ElevatorFactory elevatorFactory;

    public ElevatorSystem(int capacity, Scheduler scheduler, ElevatorFactory elevatorFactory, int numberOfElevators) {
        this.elevators = new ArrayList<>();
        for(int i=0;i<numberOfElevators;i++)
        {
            this.elevators.add(elevatorFactory.createElevatorCar(capacity));
        }
        this.scheduler = scheduler;
        scheduler.setElevators(elevators);
        this.elevatorFactory = elevatorFactory;
    }

    public void processRequest(Request request){
        ElevatorCar assignedElevator = scheduler.assignElevator(request);
        if(assignedElevator != null){
            System.out.println("assigned elevator is at floor: " + assignedElevator.currentFloor);
            assignedElevator.addRequest(request);
            assignedElevator.operate();
        }else {
            System.out.println("No elevator is available");
        }
    }
}
