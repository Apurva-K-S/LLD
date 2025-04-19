package com.example.Chess;

public class Queen extends Piece {
    public Queen(Color color)
    {
        super(color);
    }

    @Override
    public boolean canMove(Board board, Cell start, Cell end) {
        int x1 = start.getX();
        int x2 = end.getX();
        int y1 = start.getY();
        int y2 = end.getY();
        return (x1==x2 || y1==y2) || (Math.abs(x2-x1) == Math.abs(y2-y1));
    }
}
