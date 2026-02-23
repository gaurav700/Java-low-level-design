package com.pm.entity;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {

    private final Map<User, Map<User, Double>> balances = new HashMap<>();

    public void updateBalances(User paidBy, User owedBy, double amount) {
        if(paidBy.equals(owedBy)) {
            return;
        }
        balances.putIfAbsent(paidBy, new HashMap<>());
        balances.putIfAbsent(owedBy, new HashMap<>());

        balances.get(paidBy)
                .put(owedBy, balances.get(paidBy).getOrDefault(owedBy, 0.0) + amount);

        balances.get(owedBy)
                .put(paidBy, balances.get(owedBy).getOrDefault(paidBy, 0.0) - amount);
    }

    public void showAllBalances() {
        for(User user : balances.keySet()) {
            for(Map.Entry<User, Double> entry : balances.get(user).entrySet()) {
                if(entry.getValue()>0){
                    System.out.println(
                            entry.getKey().getName()
                            + " owes "
                            + user.getName()
                            + " : "
                            +entry.getValue()
                    );
                }
            }
        }
    }


    public void showBalance(User user) {
        if(!balances.containsKey(user)) {
            System.out.println("No balance for user");
            return;
        }

        for(Map.Entry<User, Double> entry : balances.get(user).entrySet()) {
            double amount = entry.getValue();

            if (amount > 0) {
                System.out.println(
                        entry.getKey().getName()
                                + " owes "
                                + user.getName()
                                + " : "
                                + amount
                );
            } else if (amount < 0) {
                System.out.println(
                        user.getName()
                                + " owes "
                                + entry.getKey().getName()
                                + " : "
                                + (-amount)
                );
            }
        }
    }

    public void settleUp(User from, User to, double amount) {
        updateBalances(from, to, amount);
    }
}