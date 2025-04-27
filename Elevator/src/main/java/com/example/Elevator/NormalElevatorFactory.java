package com.example.Elevator;

public class NormalElevatorFactory implements ElevatorFactory{
    @Override
    public ElevatorCar createElevatorCar(int capacity) {
        return new ElevatorCar(capacity,0);
    }
}
