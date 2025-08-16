package com.example.DigitalWallet.Factory;

import com.example.DigitalWallet.Entity.BankAccount;
import com.example.DigitalWallet.Enum.AccountStatus;
import com.example.DigitalWallet.Enum.AccountType;

import java.time.Instant;
import java.util.UUID;

public class BankAccountFactory {

    public BankAccount createBankAccount(AccountType type, String accountNumber,
                                         UUID userId, String bankName, String ifscCode,
                                         UUID linkedWalletId, boolean isPrimary) {
        BankAccount account = new BankAccount();
        account.setAccountNumber(accountNumber);
        account.setUserId(userId);
        account.setAccountType(type);
        account.setBalance(0.0f);
        account.setAccountStatus(AccountStatus.PENDING); // could be ACTIVE after validation
        account.setCreatedAt(Instant.now());
        account.setBankName(bankName);
        account.setIfscCode(ifscCode);
        account.setLinkedWalletId(linkedWalletId);
        account.setPrimary(isPrimary);

//        // Type‑specific initializations
//        if (type == AccountType.CURRENT) {
//            // e.g., different min balance logic later
//        }
        if (type == AccountType.CURRENT) {
            account.setBalance(5000.0f); // enforce higher minimum opening balance
            account.setAccountStatus(AccountStatus.REVIEW_PENDING); // maybe manual review for business accounts
        }

        if (type == AccountType.SAVINGS) {
            account.setBalance(1000.0f); // lower min balance
        }

        return account;
    }
}
