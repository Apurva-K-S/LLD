package com.example.Splitwise.Service;

import com.example.Splitwise.Entity.LedgerEntry;
import com.example.Splitwise.Entity.User;
import com.example.Splitwise.Enum.LedgerType;

import java.time.LocalDateTime;
import java.util.*;

/*
 private String id;
    private String groupId;
    private double amount;
    private User fromUser;
    private User toUser;
    private LocalDateTime createdAt;
    private LedgerType ledgerType;
 */
public class LedgerEntryService {
    Map<String, LedgerEntry> ledgers;
    UserService userService;

    public LedgerEntryService(UserService userService) {
        this.ledgers = new HashMap<>();
        this.userService = userService;
    }

    public LedgerEntry createLedgerEntry(String groupId, double amount, String fromUserId, String toUserId, LedgerType ledgerType)
    {
        UUID uuid = UUID.randomUUID();
        User fromUser = userService.getUserById(fromUserId);
        User toUser = userService.getUserById(toUserId);
        if(fromUser == null || toUser == null)
        {
            System.out.println("fromUser or toUser is null");
            return null;
        }
        LedgerEntry ledgerEntry = new LedgerEntry(uuid.toString(), groupId, amount, fromUser, toUser, ledgerType);
        ledgers.put(uuid.toString(), ledgerEntry);
        return ledgerEntry;
    }

    public List<LedgerEntry> getRecordsByUserId(String userId)
    {
        List<LedgerEntry> resultLedgers = new ArrayList<>();
        for(LedgerEntry ledger: ledgers.values())
        {
            if(ledger.getFromUser().getId().equals(userId))
            {
                resultLedgers.add(ledger);
            }
        }
        return resultLedgers;
    }

    public List<LedgerEntry> getRecordsByGroupId(String groupId) {
        List<LedgerEntry> resultLedgers = new ArrayList<>();
        for (LedgerEntry ledger : ledgers.values()) {
            if (ledger.getGroupId().equals(groupId)) {
                resultLedgers.add(ledger);
            }
        }
        return resultLedgers;
    }
}
