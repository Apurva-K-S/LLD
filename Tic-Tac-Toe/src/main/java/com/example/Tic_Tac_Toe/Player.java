package com.example.Tic_Tac_Toe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
public class Player {
    Coin coin;

    String name;
    String phoneNumber;

    public Player(Coin coin, String name, String phonenumber) {
        this.coin = coin;
        this.name=name;
        this.phoneNumber=phonenumber;
    }

    public String toString() {
        return this.name + " " + phoneNumber + " " + coin.symbol;
    }
}
