package com.example.DigitalWallet.Builder;

import com.example.DigitalWallet.Entity.BankAccount;
import com.example.DigitalWallet.Enum.AccountStatus;
import com.example.DigitalWallet.Enum.AccountType;

import java.time.Instant;
import java.util.UUID;

public class BankAccountBuilder {
    private final BankAccount account;

    public BankAccountBuilder() { this.account = new BankAccount(); }
    public BankAccountBuilder(BankAccount existing) { this.account = existing; }

    public BankAccountBuilder accountNumber(String accountNumber) { account.setAccountNumber(accountNumber); return this; }
    public BankAccountBuilder userId(UUID userId) { account.setUserId(userId); return this; }
    public BankAccountBuilder accountType(AccountType type) { account.setAccountType(type); return this; }
    public BankAccountBuilder balance(float balance) { account.setBalance(balance); return this; }
    public BankAccountBuilder status(AccountStatus status) { account.setAccountStatus(status); return this; }
    public BankAccountBuilder createdAt(Instant createdAt) { account.setCreatedAt(createdAt); return this; }
    public BankAccountBuilder bankName(String bankName) { account.setBankName(bankName); return this; }
    public BankAccountBuilder ifscCode(String ifscCode) { account.setIfscCode(ifscCode); return this; }
    public BankAccountBuilder linkedWalletId(UUID walletId) { account.setLinkedWalletId(walletId); return this; }
    public BankAccountBuilder primary(boolean primary) { account.setPrimary(primary); return this; }

    public BankAccount build() { return account; }
}