package com.example.DigitalWallet;

import com.example.DigitalWallet.Builder.WalletTxnBuilder;
import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Entity.User;
import com.example.DigitalWallet.Entity.Wallet;
import com.example.DigitalWallet.Enum.ParticipantType;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Enum.TransactionType;
import com.example.DigitalWallet.Enum.UserType;
import com.example.DigitalWallet.External.BankPort;
import com.example.DigitalWallet.External.InMemoryBankAdapter;
import com.example.DigitalWallet.External.InMemoryWalletAdapter;
import com.example.DigitalWallet.External.WalletPort;
import com.example.DigitalWallet.Factory.TransactionStrategyFactory;
import com.example.DigitalWallet.Service.TransactionService;
import com.example.DigitalWallet.Service.TransactionServiceImpl;
import com.example.DigitalWallet.Service.UserService;
import com.example.DigitalWallet.Service.WalletService;
import com.example.DigitalWallet.Strategy.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class DigitalWalletApplication {

	public static void main(String[] args) {
		Map<String, WalletCreditStrategy> creditStrategies = new HashMap<>();
		creditStrategies.put("BANK", new BankTransferCreditStrategy(/* deps */));

		Map<String, WalletDebitStrategy> debitStrategies = new HashMap<>();
		debitStrategies.put("BANK", new PurchaseDebitStrategy(/* deps */));

		WalletService walletService = new WalletService(creditStrategies, debitStrategies);
		UserService userService = new UserService();

		// 1. Prepare all transaction strategies you already have
		BankPort bankPort = new InMemoryBankAdapter();
		WalletPort walletPort = new InMemoryWalletAdapter();

		List<TransactionStrategy> strategies = List.of(
				new BankToWalletTransactionStrategy(bankPort, walletPort),
				new WalletToWalletTransactionStrategy(walletPort),
				new WalletToBankTransactionStrategy(walletPort, bankPort)
		);

		TransactionStrategyFactory strategyFactory = new TransactionStrategyFactory(strategies);

		TransactionService transactionService = new TransactionServiceImpl(strategyFactory);


		// 1. Create Users
		User user1 = userService.createUser(UserType.INDIVIDUAL, "Apurva", "8500809588", "sambhaviapurva0205@gmail.com");
		User user2 = userService.createUser(UserType.MERCHANT, "ManiFoods", "9925372564", "manifoods@gmail.com");

		// 2. Create Wallets and Link
		Wallet u1w1 = walletService.createWallet(user1.getId(), "INR", "1234",100000, 3000000, true);
		Wallet u1w2 = walletService.createWallet(user1.getId(), "INR", "5678",5000,150000, false);
		Wallet u2w1 = walletService.createWallet(user2.getId(), "INR", "9999",10000,300000, true);

		// 4. Add funds to wallet (Bank → Wallet)
		UUID bankAcc1 = UUID.randomUUID();
		UUID bankAcc2 = UUID.randomUUID();
		bankPort.createAccount(bankAcc1, 50_000f);
		bankPort.createAccount(bankAcc2, 10_000f);

		Transaction bankToWalletAddMoneyTxn = new WalletTxnBuilder()
				.sender(bankAcc1, ParticipantType.BANK)
				.receiver(u1w1.getId(), ParticipantType.WALLET)
				.type(TransactionType.BANK_TO_WALLET)
				.amount(2500f)
				.description("Initial funding")
				.status(TransactionStatus.PENDING)
				.build();
		transactionService.startTransaction(bankToWalletAddMoneyTxn);
		transactionService.validateTransaction(bankToWalletAddMoneyTxn.getTransactionId());
		transactionService.executeTransaction(bankToWalletAddMoneyTxn.getTransactionId());

		// 5. Transfer Wallet → Wallet (User1 -> User2)
		Transaction sendFriend = new WalletTxnBuilder()
				.sender(u1w1.getId(), ParticipantType.WALLET)
				.receiver(u2w1.getId(), ParticipantType.WALLET)
				.type(TransactionType.WALLET_TO_WALLET)
				.amount(600f)
				.description("Send to friend")
				.status(TransactionStatus.PENDING)
				.build();
		transactionService.startTransaction(sendFriend);
		transactionService.validateTransaction(sendFriend.getTransactionId());
		transactionService.executeTransaction(sendFriend.getTransactionId());

		// 6. View history
		transactionService.listTransactionsByUser(u1w1.getId())
				.forEach(System.out::println);

		// 7. Withdraw Wallet → Bank
		Transaction walletToBankReturnTxn = new WalletTxnBuilder()
				.sender(u1w1.getId(), ParticipantType.WALLET)
				.receiver(bankAcc2, ParticipantType.BANK)
				.type(TransactionType.WALLET_TO_BANK)
				.amount(400f)
				.description("Withdraw to bank")
				.status(TransactionStatus.PENDING)
				.build();
		transactionService.startTransaction(walletToBankReturnTxn);
		transactionService.validateTransaction(walletToBankReturnTxn.getTransactionId());
		transactionService.executeTransaction(walletToBankReturnTxn.getTransactionId());

		// 8 & 9. Balance / limit validations are naturally enforced inside services

		// 10. Fraud detection triggered on rapid Bank → Wallet calls
		for (int i = 0; i < 7; i++) {
			Transaction rapid = new WalletTxnBuilder()
					.sender(bankAcc1, ParticipantType.BANK)
					.receiver(u1w2.getId(), ParticipantType.WALLET)
					.type(TransactionType.BANK_TO_WALLET)
					.amount(50f)
					.description("Rapid top-up " + i)
					.status(TransactionStatus.PENDING)
					.build();
			try {
				transactionService.startTransaction(rapid);
				transactionService.validateTransaction(rapid.getTransactionId());
				transactionService.executeTransaction(rapid.getTransactionId());
			} catch (Exception e) {
				System.out.println("Fraud check blocked: " + e.getMessage());
			}
		}
	}

}
