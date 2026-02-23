package com.pm.service;

import com.pm.entity.BalanceSheet;
import com.pm.entity.Split;
import com.pm.entity.User;
import com.pm.entity.enums.SplitType;
import com.pm.strategy.SplitStrategy;
import com.pm.strategy.impl.EqualSplitStrategy;
import com.pm.strategy.impl.ExactSplitStrategy;
import com.pm.strategy.impl.PercentageSplitStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    private final Map<String, User> users = new HashMap<>();
    private final BalanceSheet balanceSheet = new BalanceSheet();

    public String addUser(String userId, String name) {
        if(users.containsKey(userId)) {
            throw new IllegalArgumentException("User with name " + name + " already exists");
        }
        User user = new User(userId, name);
        users.put(userId, user);
        return user.toString();
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void addExpense(
            String expenseId,
            String paidById,
            double amount,
            SplitType splitType,
            List<Split> splits
    ) {
        User paidBy = users.get(paidById);

        if(paidBy == null) {
            throw new IllegalArgumentException("User with id " + paidById + " does not exist");
        }

        SplitStrategy strategy = getStrategy(splitType);
        strategy.validate(amount, splits);
        strategy.calculate(amount, splits);
        for(Split split : splits) {
            User owedBy = split.getUser();
            if(!owedBy.equals(paidBy)) {
                balanceSheet.updateBalances(paidBy, owedBy, split.getAmount());
            }
        }

    }

    public void showBalances() {
        balanceSheet.showAllBalances();
    }

    public void showBalanceForUser(String userId) {
        User user = users.get(userId);
        if(user == null) {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }
        balanceSheet.showBalance(user);
    }

    public void settleUp(String fromUserId, String toUserId, double amount) {
        User from = users.get(fromUserId);
        User to = users.get(toUserId);

        if(from == null || to == null) {
            throw new IllegalArgumentException("User with id does not exist");
        }

        balanceSheet.settleUp(from, to, amount);
    }

    private SplitStrategy getStrategy(SplitType splitType) {
        return switch (splitType) {
            case EQUAL -> new EqualSplitStrategy();
            case EXACT -> new ExactSplitStrategy();
            case PERCENTAGE -> new PercentageSplitStrategy();
            default -> throw new IllegalArgumentException("Invalid splitType");
        };
    }
}