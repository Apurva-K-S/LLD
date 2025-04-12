package com.example.Tic_Tac_Toe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class Board {
    int size;

    Cell[][] cells;
    public Board(int size)
    {
        this.size=size;
        cells = new Cell[size][size];
        initializeCells(cells);
    }
    public void initializeCells(Cell cells[][])
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                cells[i][j]=new Cell(i,j,new Coin("-"));
            }
        }
    }
    public void printBoard()
    {
        System.out.print("printing board\n ");
        for(int i=0;i<size;i++)
            System.out.print(" " + i);
        System.out.println("");
        for(int i=0;i<size;i++)
        {
            System.out.print(i + " ");
            for(int j=0;j<size;j++)
            {
                System.out.print(cells[i][j].coin.symbol +" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
