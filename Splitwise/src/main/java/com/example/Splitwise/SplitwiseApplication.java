package com.example.Splitwise;

import com.example.Splitwise.Entity.Expense;
import com.example.Splitwise.Entity.Group;
import com.example.Splitwise.Entity.LedgerEntry;
import com.example.Splitwise.Entity.User;
import com.example.Splitwise.Enum.CurrencyType;
import com.example.Splitwise.Enum.LedgerType;
import com.example.Splitwise.Enum.SplitType;
import com.example.Splitwise.Service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SplitwiseApplication {

	public static void main(String[] args) {
		// 1. The system should allow users to create accounts and manage their profile information.
		UserService userService = new UserService();
		User apurva = userService.CreateUser("Apurva", "apurva@gmail.com");
		User dheer = userService.CreateUser("Dheer", "dheer@gmail.com");

		System.out.println(apurva);
		System.out.println(dheer);

		// 2. Users should be able to create groups and add other users to the groups.
		GroupService groupService = new GroupService(userService);
		Group group1 = groupService.createGroup("group1", CurrencyType.INR, apurva.getId());
		System.out.println(group1);
		groupService.addUserToGroup(group1.getId(), dheer.getId());
		System.out.println(group1);

		// 3. Users should be able to add expenses within a group, specifying the amount, description, and participants.
		SplitStrategyService splitStrategyService = new PercentageSplitStrategyService();
		SplitStrategyFactory splitStrategyFactory = new SplitStrategyFactory();
		SplitService splitService = new SplitService(splitStrategyFactory);
		LedgerEntryService ledgerEntryService = new LedgerEntryService(userService);
		ExpenseService expenseService = new ExpenseService(splitService,groupService, userService, ledgerEntryService);

		Map<String, Double> userPercentageSplit = new HashMap<>();
		userPercentageSplit.put(dheer.getId(), 0.6);
		userPercentageSplit.put(apurva.getId(), 0.4);
		Expense expense = expenseService.addExpenseToGroup(apurva.getId(), 1000, group1.getId(), SplitType.PERCENTAGE, userPercentageSplit);

		System.out.println("\n");
		System.out.println(expense);
		System.out.println("\n");

		// 4. Users should be able to view their individual balances with other users and settle up the balances.
		LedgerEntry ledgerEntry = ledgerEntryService.createLedgerEntry(group1.getId(),500, dheer.getId(), apurva.getId(), LedgerType.SETTLEMENT_CREDIT);
		System.out.println("\nLedger Entries");
		System.out.println(ledgerEntry);
		System.out.println("\n");

		// 5. Users should be able to view their individual balances with other users and settle up the balances.
		BalanceService balanceService = new BalanceService(ledgerEntryService, groupService, userService);
		System.out.println("\nBalances");
		balanceService.getBalanceByGroupId(group1.getId());
		System.out.println("\n");
	}

}
