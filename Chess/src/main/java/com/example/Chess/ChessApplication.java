package com.example.Chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ChessApplication {

	public static void main(String[] args) {

		SpringApplication.run(ChessApplication.class, args);
		System.out.println("This is chess");

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Player1 (white) name: ");
		String name = sc.next();
		Player whitePlayer = new Player(name, Color.WHITE);
		System.out.println();
		System.out.print("Enter Player2 (black) name: ");
		name = sc.next();
		Player blackPlayer = new Player(name, Color.BLACK);
		Game game = new Game(whitePlayer, blackPlayer);

		boolean gameIsRunning = true;
		while (gameIsRunning){
			game.printBoard();
			System.out.println("Current Player: "+ game.currentPlayer.getName() + " with color: " + game.currentPlayer.getColor());
			System.out.print("Which cell piece you want to move (x, y) both x and y between 0 and 7: ");
			int startX = sc.nextInt();
			int startY = sc.nextInt();
			System.out.println();
			System.out.print("To which position you want to move the piece (x, y) both x and y between 0 and 7: ");
			int nextX = sc.nextInt();
			int nextY = sc.nextInt();
			gameIsRunning = game.playerMove(startX, startY, nextX, nextY);
			if(!gameIsRunning)
			{
				System.out.println("END");
				break;
			}
		}

	}

}
