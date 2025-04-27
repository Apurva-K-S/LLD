package com.example.Elevator;

import java.util.List;

public interface Scheduler {
    ElevatorCar assignElevator(Request request);
    void setElevators(List<ElevatorCar> elevators);
}
