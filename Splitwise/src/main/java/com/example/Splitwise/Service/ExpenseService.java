package com.example.Splitwise.Service;

import com.example.Splitwise.Entity.Expense;
import com.example.Splitwise.Entity.LedgerEntry;
import com.example.Splitwise.Entity.Split;
import com.example.Splitwise.Entity.User;
import com.example.Splitwise.Enum.LedgerType;
import com.example.Splitwise.Enum.SplitType;

import java.util.*;

public class ExpenseService {
    Map<String, Expense> expenses;

    SplitService splitService;
    GroupService groupService;
    UserService userService;
    LedgerEntryService ledgerEntryService;


    public ExpenseService(SplitService splitService, GroupService groupService, UserService userService, LedgerEntryService ledgerEntryService)
    {
        this.expenses = new HashMap<>();
        this.splitService = splitService;
        this.groupService = groupService;
        this.userService = userService;
        this.ledgerEntryService = ledgerEntryService;
    }

    public Expense addExpenseToGroup(String userIdPaidBy, double totalAmount, String groupId, SplitType splitType, Map<String, Double> userSplitData)
    {
        UUID uuid = UUID.randomUUID();
        SplitStrategyService splitStrategyService = splitService.splitStrategyFactory.getStrategy(splitType);
        List<User> users = new ArrayList<>();
        for(String userId: userSplitData.keySet())
        {
            User user = userService.getUserById(userId);
            if(user!=null)
                users.add(user);
        }
        List<Split> splits = splitStrategyService.computeSplit(totalAmount, users, userSplitData);
        User paidBy = userService.getUserById(userIdPaidBy);
        if(paidBy == null)
        {
            System.out.println("User paidBy is null");
            return null;
        }

        Expense expense = new Expense(splits,splitType,groupId,totalAmount,paidBy,uuid.toString());
        expenses.put(uuid.toString(), expense);
        for(Split split : splits) {
            ledgerEntryService.createLedgerEntry(groupId, split.getAmount(), userIdPaidBy, split.getUser().getId(), LedgerType.EXPENSE_DEBIT);
        }
        return expense;
    }

}
