package com.example.Tic_Tac_Toe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class Coin {
    String symbol;

    public Coin(String symbol) {
        this.symbol=symbol;
    }
}
