package com.example.DigitalWallet.Service;

import com.example.DigitalWallet.Builder.WalletTxnBuilder;
import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Entity.Wallet;
import com.example.DigitalWallet.Enum.ParticipantType;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Enum.TransactionType;
import com.example.DigitalWallet.Enum.WalletStatus;
import com.example.DigitalWallet.Strategy.WalletCreditStrategy;
import com.example.DigitalWallet.Strategy.WalletDebitStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class WalletService {

    private final Map<UUID, Wallet> walletStore = new ConcurrentHashMap<>();
    private final Map<UUID, List<Transaction>> transactionStore = new ConcurrentHashMap<>();

    private final Map<String, WalletCreditStrategy> creditStrategies;
    private final Map<String, WalletDebitStrategy> debitStrategies;

    public WalletService(Map<String, WalletCreditStrategy> creditStrategies,
                         Map<String, WalletDebitStrategy> debitStrategies) {
        this.creditStrategies = creditStrategies;
        this.debitStrategies = debitStrategies;
    }

    // CreateWallet
    public Wallet createWallet(UUID userId, String currency, String pin,
                               float dailyLimit, float monthlyLimit, boolean isDefault) {
        Wallet wallet = new Wallet(UUID.randomUUID(), userId, pin, 0f, 0f,
                WalletStatus.ACTIVE, currency, dailyLimit, monthlyLimit, isDefault);
        walletStore.put(wallet.getId(), wallet);
        transactionStore.put(wallet.getId(), new ArrayList<>());
        emitEvent(wallet);
        return wallet;
    }

    public void emitEvent(Wallet wallet) {
        System.out.println("wallet details => "+
                "id: "+ wallet.getId() +
                "| currency: "+ wallet.getCurrency()+
                "| current balance: " + wallet.getCurrentBalance() +
                "| daily limit: "+ wallet.getDailyTransactionLimit() +
                "| monthly limit: "+ wallet.getMonthlyTransactionLimit() +
                "| belongs to: " + wallet.getUserId());
    }

    // GetBalance
    public float getBalance(UUID walletId) {
        Wallet wallet = getWallet(walletId);
        return wallet.getCurrentBalance();
    }

    // CreditWallet
    public boolean creditWallet(UUID walletId, float amount, String source, String description) {
        Wallet wallet = getWallet(walletId);
        WalletCreditStrategy strategy = creditStrategies.get(source);
        if (strategy == null) throw new IllegalArgumentException("Unknown credit source: " + source);

        boolean success = strategy.credit(wallet, amount);
        if (success) {
            recordTransaction(wallet, amount, TransactionType.BANK_TO_WALLET, "CREDIT", description);
            // Observer hook — could publish WalletCredited event here
        }
        emitEvent(wallet);
        return success;
    }

    // DebitWallet
    public boolean debitWallet(UUID walletId, float amount, String source, String description) {
        Wallet wallet = getWallet(walletId);
        WalletDebitStrategy strategy = debitStrategies.get(source);
        if (strategy == null) throw new IllegalArgumentException("Unknown debit source: " + source);

        boolean success = strategy.debit(wallet, amount);
        if (success) {
            recordTransaction(wallet, amount, TransactionType.WALLET_TO_BANK, "DEBIT", description);
            // Observer hook — could publish WalletDebited / BalanceLow event
        }
        emitEvent(wallet);
        return success;
    }

    // GetTransactionHistory
    public List<Transaction> getTransactionHistory(UUID walletId) {
        return Collections.unmodifiableList(transactionStore.getOrDefault(walletId, new ArrayList<>()));
    }

    // Helpers
    private Wallet getWallet(UUID walletId) {
        Wallet wallet = walletStore.get(walletId);
        if (wallet == null) throw new NoSuchElementException("Wallet not found: " + walletId);
        return wallet;
    }

    private void recordTransaction(Wallet wallet, float amount, TransactionType txnType,
                                   String creditOrDebit, String description) {
        Transaction txn = new WalletTxnBuilder()
                .sender(wallet.getId(), ParticipantType.WALLET)
                .amount(amount)
                .type(txnType)
                .description(description)
                .status(TransactionStatus.SUCCESS)
                .build();
        transactionStore.get(wallet.getId()).add(txn);
    }
}
