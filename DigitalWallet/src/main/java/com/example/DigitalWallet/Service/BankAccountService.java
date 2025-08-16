package com.example.DigitalWallet.Service;

import com.example.DigitalWallet.Builder.BankAccountBuilder;
import com.example.DigitalWallet.Entity.BankAccount;
import com.example.DigitalWallet.Enum.AccountStatus;
import com.example.DigitalWallet.Enum.AccountType;
import com.example.DigitalWallet.Factory.BankAccountFactory;
import com.example.DigitalWallet.Strategy.BankAccountValidationStrategy;
import com.example.DigitalWallet.Strategy.SavingsAccountValidationStrategy;
import com.example.DigitalWallet.Strategy.CurrentAccountValidationStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BankAccountService {

    private final Map<String, BankAccount> accountStore = new ConcurrentHashMap<>();
    private final BankAccountFactory factory = new BankAccountFactory();

    // A registry of strategies keyed by AccountType
    private final Map<AccountType, BankAccountValidationStrategy> validators = Map.of(
            AccountType.SAVINGS, new SavingsAccountValidationStrategy(),
            AccountType.CURRENT, new CurrentAccountValidationStrategy()
    );

    // LinkBankAccount
    public BankAccount linkBankAccount(AccountType type, String accountNumber, UUID userId,
                                       String bankName, String ifscCode, UUID linkedWalletId,
                                       boolean isPrimary) {

        BankAccount account = factory.createBankAccount(type, accountNumber, userId, bankName, ifscCode, linkedWalletId, isPrimary);

        if (!validateBankDetails(account)) {
            throw new IllegalArgumentException("Invalid bank account details");
        }

        account.setAccountStatus(AccountStatus.ACTIVE);
        accountStore.put(account.getAccountNumber(), account);
        emitEvent("BANK_ACCOUNT_LINKED", account);
        return account;
    }

    // UnlinkBankAccount (soft close)
    public void unlinkBankAccount(String accountNumber) {
        BankAccount acc = accountStore.get(accountNumber);
        if (acc != null) {
            acc.setAccountStatus(AccountStatus.CLOSED);
            emitEvent("BANK_ACCOUNT_UNLINKED", acc);
        }
    }

    // ValidateBankDetails
    public boolean validateBankDetails(BankAccount account) {
        BankAccountValidationStrategy strategy = validators.get(account.getAccountType());
        return strategy != null && strategy.validate(account);
    }

    // GetBankAccountsByUser
    public List<BankAccount> getBankAccountsByUser(UUID userId) {
        List<BankAccount> result = new ArrayList<>();
        for (BankAccount acc : accountStore.values()) {
            if (acc.getUserId().equals(userId)) {
                result.add(acc);
            }
        }
        return result;
    }

    // SetPrimaryAccount
    public void setPrimaryAccount(UUID userId, String accountNumber) {
        for (BankAccount acc : accountStore.values()) {
            if (acc.getUserId().equals(userId)) {
                acc.setPrimary(acc.getAccountNumber().equals(accountNumber));
            }
        }
    }

    // UpdateBankAccountStatus
    public void updateBankAccountStatus(String accountNumber, AccountStatus status) {
        BankAccount acc = accountStore.get(accountNumber);
        if (acc != null) {
            acc.setAccountStatus(status);
            emitEvent("BANK_ACCOUNT_STATUS_CHANGED", acc);
        }
    }

    private void emitEvent(String eventType, BankAccount account) {
        System.out.printf("Event: %s -> Account: %s%n", eventType, account.getAccountNumber());
    }
    // to set number of bank accounts for the user, we can do one thing, as the bankaccount gets linked, raise an event, user service will consumer this event and it will update the bank account details of the user.

    // to get primary account for a particular user,
    // for this, we can also do like - traverse all accounts of user and return primaryBankAccount, but here we want to separate out all bank related operations to BankAccountService.
    public Optional<BankAccount> getPrimaryBankAccount(UUID userId) {
        return accountStore.values().stream()
                .filter(acc -> acc.getUserId().equals(userId) && acc.isPrimary())
                .findFirst();
    }

}
