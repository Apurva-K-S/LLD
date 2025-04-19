package com.example.Chess;

public abstract class Piece {
    Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean canMove(Board board, Cell start, Cell end);
}
