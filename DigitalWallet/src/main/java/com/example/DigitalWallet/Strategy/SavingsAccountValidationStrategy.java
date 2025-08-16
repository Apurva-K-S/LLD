package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.BankAccount;
import com.example.DigitalWallet.Strategy.BankAccountValidationStrategy;

public class SavingsAccountValidationStrategy implements BankAccountValidationStrategy {
    @Override
    public boolean validate(BankAccount account) {
        return account.getAccountNumber().matches("\\d{6,8}") // 6 to 8 digits.
                && account.getIfscCode().matches("^[A-Z]{2}0[A-Z0-9]{6}$"); // first 4 chars must be A-Z, last 6 chars can be A-Z or 0-9
    }
}