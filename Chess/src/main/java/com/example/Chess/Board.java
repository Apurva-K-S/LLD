package com.example.Chess;

public class Board {
    Cell [][]cells;

    public Board() {
        this.cells = new Cell[8][8];
        resetBoard();
    }
    public void resetBoard(){
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                this.cells[i][j] = new Cell(i, j, null);
            }
        }
        //R Kn B K Q B Kn R
        this.cells[0][0].setPiece(new Rook(Color.BLACK));
        this.cells[0][1].setPiece(new Knight(Color.BLACK));
        this.cells[0][2].setPiece(new Bishop(Color.BLACK));
        this.cells[0][3].setPiece(new Queen(Color.BLACK));
        this.cells[0][4].setPiece(new King(Color.BLACK));
        this.cells[0][5].setPiece(new Bishop(Color.BLACK));
        this.cells[0][6].setPiece(new Knight(Color.BLACK));
        this.cells[0][7].setPiece(new Rook(Color.BLACK));
        for(int i=0;i<8;i++)
        {
            this.cells[1][i].setPiece(new Pawn(Color.BLACK));
        }

        //R Kn B K Q B Kn R
        this.cells[7][0].setPiece(new Rook(Color.WHITE));
        this.cells[7][1].setPiece(new Knight(Color.WHITE));
        this.cells[7][2].setPiece(new Bishop(Color.WHITE));
        this.cells[7][3].setPiece(new Queen(Color.WHITE));
        this.cells[7][4].setPiece(new King(Color.WHITE));
        this.cells[7][5].setPiece(new Bishop(Color.WHITE));
        this.cells[7][6].setPiece(new Knight(Color.WHITE));
        this.cells[7][7].setPiece(new Rook(Color.WHITE));
        for(int i=0;i<8;i++)
        {
            this.cells[6][i].setPiece(new Pawn(Color.WHITE));
        }
    }

    public Cell getCell(int x, int y){
        if(x<0 || x>7 || y<0 || y>7)
            return null;
        return this.cells[x][y];
    }
}
