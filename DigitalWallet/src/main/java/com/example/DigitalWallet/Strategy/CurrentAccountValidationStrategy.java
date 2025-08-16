package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.BankAccount;
import com.example.DigitalWallet.Strategy.BankAccountValidationStrategy;

public class CurrentAccountValidationStrategy implements BankAccountValidationStrategy {
    @Override
    public boolean validate(BankAccount account) {
        // Could enforce different rules for current accounts
        return account.getAccountNumber().matches("\\d{4,6}")
                && account.getIfscCode().matches("^[A-Z]{2}0[A-Z0-9]{2}$");
    }
}