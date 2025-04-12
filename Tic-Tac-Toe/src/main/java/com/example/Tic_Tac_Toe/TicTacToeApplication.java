package com.example.Tic_Tac_Toe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Scanner;

@SpringBootApplication
public class TicTacToeApplication {

    static Player player1;
    static Player player2;
    static Board board;

	static int totalRounds;
	static int currentRound;
	static int boardSize;
	static HashMap<Integer, String> roundStatus = new HashMap<>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeApplication.class, args);

		System.out.println("Please enter number of rounds in tournament: ");
		totalRounds = sc.nextInt();

		System.out.println("Please enter board size: ");
		boardSize = sc.nextInt();

		board = new Board(boardSize);

		System.out.println("your inital board: ");
		board.printBoard();

		System.out.println("Please enter player1 name, phonenumber and symbol: ");
		String name = sc.next();
		String phonenumber = sc.next();
		String symbol = sc.next();
		player1 = new Player(new Coin(symbol), name, phonenumber);

		System.out.println("Please enter player2 name, phonenumber and symbol: ");
		name = sc.next();
		phonenumber = sc.next();
		symbol = sc.next();
		player2 = new Player(new Coin(symbol), name, phonenumber);

		System.out.println("player1 details: " + player1.toString());
		System.out.println("player2 details: " + player2.toString());

		for(currentRound=0;currentRound<totalRounds;)
		{
			System.out.println("Round "+ currentRound + " starting");
			board = new Board(boardSize);
			String status = startRound(player1, player2, board);
			if(status.equals("restart"))
				continue;
			if(!status.equals("draw"))
				System.out.println(status + " won round = " + currentRound);
			else
				System.out.println(currentRound + " draw");
			roundStatus.put(currentRound, status);
			currentRound++;
		}
		System.out.println("Tournament details: ");
		int player1Count=0, player2Count=0, drawCount=0;
		for(int i: roundStatus.keySet())
		{
			if(roundStatus.get(i).equals(player1.name))
				player1Count++;
			else if(roundStatus.get(i).equals(player2.name))
				player2Count++;
			else
				drawCount++;
		}
		System.out.println(player1.name + " won " + player1Count + " rounds");
		System.out.println(player2.name + " won " + player2Count + " rounds");
		System.out.println("draw on " + drawCount + " rounds");
		if(player1Count > player2Count)
			System.out.println(player1.name + " won the tournament");
		else if(player2Count > player1Count)
			System.out.println(player2.name + " won the tournament");
		else
			System.out.println("There is tie in the tournament.");
	}

	public static String startRound(Player player1, Player player2, Board board) {
		int totalMoves = boardSize * boardSize;
		String turn = player1.name;
		for(int i=0;i<totalMoves;)
		{
			System.out.println(turn +" please select a valid move (x y): ");
			int x = sc.nextInt();
			int y = sc.nextInt();

			if(invalidMove(board, x, y))
				continue;
			i++;
			if(turn.equals(player1.name)) {
				board.cells[x][y] = new Cell(x, y, player1.coin);
			} else {
				board.cells[x][y] = new Cell(x, y, player2.coin);
			}
			board.printBoard();

			String status = getStatusOfGame(board);

			if(turn.equals(player1.name))
				turn=player2.name;
			else
				turn=player1.name;

			if(status.equals(player1.name) || status.equals(player2.name))
				return status;
		}
		return "draw";
	}

	public static String getStatusOfGame(Board board) {
		// check rows;
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < boardSize; i++) {
			for(int j=0;j<boardSize;j++)
			{
				if(!board.cells[i][j].coin.symbol.equals("-")) {
					map.put(board.cells[i][j].coin.symbol, map.getOrDefault(board.cells[i][j].coin.symbol, 0)+1);
				}
			}
			if(map.size()==1) {
				String name="";
				for(String k : map.keySet())
				{
					if(map.get(k)==boardSize)
						name=k;
				}
				if (name.equals(player1.coin.symbol)) {
					return player1.name;
				} else if (name.equals(player2.coin.symbol)) {
					return player2.name;
				}
			}
			map.clear();
		}


		// check cols
		for (int i = 0; i < boardSize; i++) {
			for(int j=0;j<boardSize;j++)
			{
				if(!board.cells[j][i].coin.symbol.equals("-")) {
					map.put(board.cells[j][i].coin.symbol, map.getOrDefault(board.cells[j][i].coin.symbol, 0)+1);
				}
			}
			if(map.size()==1) {
				String name="";
				for(String k : map.keySet())
				{
					if(map.get(k)==boardSize)
						name=k;
				}
				if (name.equals(player1.coin.symbol)) {
					return player1.name;
				} else if (name.equals(player2.coin.symbol)) {
					return player2.name;
				}
			}
			map.clear();
		}


		// check both diagonals
		// left diagonal
		map.clear();
		for(int i=0;i<boardSize;i++) {
			if(!board.cells[i][i].coin.symbol.equals("-"))
				map.put(board.cells[i][i].coin.symbol, map.getOrDefault(board.cells[i][i].coin.symbol, 0)+1);
		}
		if(map.size()==1) {
			String name="";
			for(String k : map.keySet())
			{
				if(map.get(k)==boardSize)
					name=k;
			}
			if (name.equals(player1.coin.symbol)) {
				return player1.name;
			} else if (name.equals(player2.coin.symbol)) {
				return player2.name;
			}
		}

		map.clear();
		// right diagonal.
		for(int i=0;i<boardSize;i++) {
			if(!board.cells[i][boardSize-i-1].coin.symbol.equals("-"))
				map.put(board.cells[i][boardSize-i-1].coin.symbol, map.getOrDefault(board.cells[i][boardSize-i-1].coin.symbol, 0)+1);
		}
		if(map.size()==1) {
			String name="";
			for(String k : map.keySet())
				if(map.get(k)==boardSize)
					name=k;
			if (name.equals(player1.coin.symbol)) {
				return player1.name;
			} else if (name.equals(player2.coin.symbol)) {
				return player2.name;
			}
		}
		return "continue";
	}

	public static boolean invalidMove(Board board, int x, int y)
	{
		return x<0 || x>=board.size || y<0 || y>=board.size || (!board.cells[x][y].coin.symbol.equals("-"));
	}
}
/*
diagonal didnt work.
2 times printing. - done
after winning the round, win should be printed. - done
check if already existing cell is chosen, ask user to enter again.
 */