package com.example.Elevator;

import java.util.List;

public class NearestElevatorSchedulerStrategy implements Scheduler{
    List<ElevatorCar> elevators;

    public NearestElevatorSchedulerStrategy(List<ElevatorCar> elevators) {
        this.elevators = elevators;
    }

    @Override
    public ElevatorCar assignElevator(Request request) {
        int distanceMin = Integer.MAX_VALUE;
        ElevatorCar result = null;
        for(int i=0;i<elevators.size();i++)
        {
            if(distanceMin > Math.abs(elevators.get(i).currentFloor - request.destinationFloor))
            {
                distanceMin = Math.abs(elevators.get(i).currentFloor - request.destinationFloor);
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
