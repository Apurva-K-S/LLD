package com.example.Elevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ElevatorApplication {

	public static void main(String[] args) {
		ElevatorFactory elevatorFactory = new NormalElevatorFactory();

		Scheduler scheduler = new NearestElevatorSchedulerStrategy(new ArrayList<>());
		ElevatorSystem elevatorSystem= new ElevatorSystem(10, scheduler,elevatorFactory, 2);

		// Simulate user requests to different floors
		elevatorSystem.processRequest(new Request(3, Direction.UP)); // Request from 3rd floor
		elevatorSystem.processRequest(new Request(5, Direction.UP)); // Request from 5th floor
		elevatorSystem.processRequest(new Request(2, Direction.DOWN)); // Request from 2nd floor
	}

}
