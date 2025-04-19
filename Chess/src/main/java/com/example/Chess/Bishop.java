package com.example.Chess;

public class Bishop extends Piece {
    public Bishop(Color color)
    {
        super(color);
    }
    @Override
    public boolean canMove(Board board, Cell start, Cell end) {
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return dx==dy;
    }
}
