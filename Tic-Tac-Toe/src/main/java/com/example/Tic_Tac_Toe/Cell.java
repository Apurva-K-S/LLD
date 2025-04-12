package com.example.Tic_Tac_Toe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class Cell {
    int x;
    int y;
    Coin coin;
    public Cell(int x, int y, Coin coin)
    {
        this.x=x;
        this.y=y;
        this.coin=coin;
    }

}
