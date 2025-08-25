package com.example.Splitwise.Service;

import com.example.Splitwise.Entity.Balance;
import com.example.Splitwise.Entity.Group;
import com.example.Splitwise.Entity.LedgerEntry;
import com.example.Splitwise.Entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BalanceService {
    LedgerEntryService ledgerEntryService;
    GroupService groupService;
    UserService userService;

    public BalanceService(LedgerEntryService ledgerEntryService, GroupService groupService, UserService userService)
    {
        this.ledgerEntryService = ledgerEntryService;
        this.groupService = groupService;
        this.userService = userService;
    }

    public void getBalanceByGroupId(String groupId) {
        List<LedgerEntry> records = ledgerEntryService.getRecordsByGroupId(groupId);
        Group group = groupService.getGroupById(groupId);
        if (group == null) {
            System.out.println("Group is null");
            return;
        }

        Map<String, Double> pairwiseBalances = new HashMap<>();

        for (LedgerEntry entry : records) {
            User from = entry.getFromUser();
            User to = entry.getToUser();
            double amount = entry.getAmount();

            // Create a consistent key: "A|B" where A < B
            String key = from.getId().compareTo(to.getId()) < 0
                    ? from.getId() + "|" + to.getId()
                    : to.getId() + "|" + from.getId();

            // Determine direction
            double signedAmount = from.getId().compareTo(to.getId()) < 0 ? amount : -amount;

            pairwiseBalances.merge(key, signedAmount, Double::sum);
        }

        // Print balances
        for (Map.Entry<String, Double> entry : pairwiseBalances.entrySet()) {
            String[] ids = entry.getKey().split("\\|");
            User userA = userService.getUserById(ids[0]);
            User userB = userService.getUserById(ids[1]);
            double balance = entry.getValue();

            if (Math.abs(balance) < 0.01) continue; // ignore near-zero balances

            if (balance > 0) {
                System.out.println(userA.getName() + " should get from " + userB.getName() + " ₹" + String.format("%.2f", balance));
            } else {
                System.out.println(userB.getName() + " should get from " + userA.getName() + " ₹" + String.format("%.2f", -balance));
            }
        }
    }

}
