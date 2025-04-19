package com.example.Chess;

public class Knight extends Piece{
    public Knight(Color color)
    {
        super(color);
    }
    @Override
    public boolean canMove(Board board, Cell start, Cell end) {
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
