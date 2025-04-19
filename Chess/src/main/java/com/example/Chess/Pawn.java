package com.example.Chess;

public class Pawn extends Piece{
    public Pawn(Color color)
    {
        super(color);
    }
    @Override
    public boolean canMove(Board board, Cell start, Cell end) {
        int dir = getColor() == Color.WHITE ? -1 : 1;
        int startRow = getColor() == Color.WHITE ? 6 : 1;

        // here for pawn, directions matter, thats why we are not doing math.abs()
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();

        //Final destination must also be empty (since this is not a capture) - end.isEmpty() check.

        // 1 step movement.
        if(dy==0 && dx == dir && end.isEmpty())
            return true;

        // if its first move, then 2 step movement is also allowed.
        if(dy==0 && dx == 2*dir && start.getX() == startRow)
        {
            int midX = start.getX() + dir;
            //Checks that the square in between the start and end is empty — because pawns can’t jump over pieces
            if(board.getCell(midX, start.getY()).isEmpty() && end.isEmpty())
                return true;
        }
        return false;
    }
}
