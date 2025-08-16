package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.BankAccount;

public interface BankAccountValidationStrategy {
    boolean validate(BankAccount account);
}
