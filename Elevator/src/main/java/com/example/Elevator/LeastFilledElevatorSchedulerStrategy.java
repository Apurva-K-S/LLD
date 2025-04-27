package com.example.Elevator;

import java.util.List;

public class LeastFilledElevatorSchedulerStrategy implements Scheduler{
    List<ElevatorCar> elevators;

    public LeastFilledElevatorSchedulerStrategy(List<ElevatorCar> elevators) {
        this.elevators = elevators;
    }

    @Override
    public ElevatorCar assignElevator(Request request) {
        int capacity = Integer.MAX_VALUE;
        ElevatorCar result = null;
        for(int i=0;i<elevators.size();i++)
        {
            if(capacity > elevators.get(i).currentCapacity)
            {
                capacity = elevators.get(i).currentFloor;
                result = elevators.get(i);
            }
        }
        return result;
    }

    @Override
    public void setElevators(List<ElevatorCar> elevators) {
        this.elevators = elevators;
    }
}
