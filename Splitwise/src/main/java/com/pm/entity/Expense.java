package com.pm.entity;

import com.pm.entity.enums.SplitType;

import java.util.List;
import java.util.UUID;

public class Expense {
    private final String expenseId;
    private final User paidBy;
    private final double totalAmount;
    private final List<Split> splits;
    private final SplitType splitType;

    public Expense(String expenseId, User paidBy, double totalAmount, List<Split> splits, SplitType splitType) {
        this.expenseId = expenseId;
        this.paidBy = paidBy;
        this.totalAmount = totalAmount;
        this.splits = splits;
        this.splitType = splitType;
    }
}
