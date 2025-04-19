package com.example.Chess;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Board board;
    Player whitePlayer;
    Player blackPlayer;
    Player currentPlayer;
    GameStatus gameStatus;
    List<Move> movesList;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = whitePlayer;
        this.gameStatus = GameStatus.ACTIVE;
        this.movesList = new ArrayList<>();
    }

    public boolean playerMove(int startX, int startY, int endX, int endY) {
        System.out.println("Current player: " + currentPlayer.name);
        Cell startCell = board.cells[startX][startY];
        Cell endCell = board.cells[endX][endY];
        Piece startCellPiece = startCell.getPiece();
        if(startCellPiece == null || startCellPiece.getColor() != currentPlayer.getColor())
            return false;

        if(!startCellPiece.canMove(board, startCell, endCell)) // if the cell in the current place cant move to chosen cell do return.
            return false;

        Move move = new Move(currentPlayer, startCell, endCell);
        endCell.setPiece(startCellPiece); // current piece will go to end cell.
        startCell.setPiece(null); // current cell wont have any piece.
        movesList.add(move);

        if(move.getKilledPiece() instanceof King)
        {
            gameStatus = currentPlayer.getColor() == Color.WHITE ? GameStatus.WHITE_WIN : GameStatus.BLACK_WIN;
            System.out.println(gameStatus);
            return false;
        }
        else {
            currentPlayer = currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
        }
        return true;
    }

    public void printBoard() {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                Piece piece = board.cells[i][j].getPiece();
                String name=getName(piece);
                System.out.print(name + " ");
            }
            System.out.println();
        }
    }

    public String getName(Piece piece) {
        String name="-";
        if(piece == null)
            return name;
        if(piece.getColor() == Color.BLACK) {
            if (piece instanceof King)
                name = "kg_b";
            else if (piece instanceof Queen)
                name = "qu_b";
            else if (piece instanceof Knight)
                name = "kn_b";
            else if(piece instanceof Bishop)
                name = "bh_b";
            else if(piece instanceof Rook)
                name = "rk_b";
            else if(piece instanceof Pawn)
                name = "pn_b";
        } else {
            if (piece instanceof King)
                name = "kg_w";
            else if (piece instanceof Queen)
                name = "qu_w";
            else if (piece instanceof Knight)
                name = "kn_w";
            else if(piece instanceof Bishop)
                name = "bh_w";
            else if(piece instanceof Rook)
                name = "rk_w";
            else if(piece instanceof Pawn)
                name = "pn_w";
        }
        return name;
    }
}
