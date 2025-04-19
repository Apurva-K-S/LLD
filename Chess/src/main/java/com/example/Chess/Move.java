package com.example.Chess;

public class Move {
    Player player;
    Cell startCell;
    Cell endCell;
    Piece movedPiece;
    Piece killedPiece;

    public Move(Player player, Cell startCell, Cell endCell) {
        this.player = player;
        this.startCell = startCell;
        this.endCell = endCell;
        this.movedPiece = startCell.getPiece();
        this.killedPiece = endCell.getPiece();
    }

    public Piece getKilledPiece(){
        return this.killedPiece;
    }
}
