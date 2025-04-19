package com.example.Chess;

public class King extends Piece {
    public King(Color color)
    {
        super(color);
    }
    @Override
    public boolean canMove(Board board, Cell start, Cell end) {
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return dx<=1 && dy<=1;
    }
}
