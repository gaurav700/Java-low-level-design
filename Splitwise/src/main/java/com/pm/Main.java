package com.pm;

import com.pm.entity.EqualSplit;
import com.pm.entity.ExactSplit;
import com.pm.entity.PercentageSplit;
import com.pm.entity.Split;
import com.pm.entity.enums.SplitType;
import com.pm.service.ExpenseManager;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        ExpenseManager manager = new ExpenseManager();

        // ===============================
        // 1️⃣ Add Users
        // ===============================
        manager.addUser("U1", "Alice");
        manager.addUser("U2", "Bob");
        manager.addUser("U3", "Charlie");

        System.out.println("Users added successfully\n");

        // ===============================
        // 2️⃣ Equal Split Example
        // Alice paid 900 for Alice, Bob, Charlie
        // ===============================

        List<Split> equalSplits = new ArrayList<>();
        equalSplits.add(new EqualSplit(manager.getUser("U1")));
        equalSplits.add(new EqualSplit(manager.getUser("U2")));
        equalSplits.add(new EqualSplit(manager.getUser("U3")));

        manager.addExpense(
                "E1",
                "U1",
                900,
                SplitType.EQUAL,
                equalSplits
        );

        System.out.println("After Equal Split Expense:");
        manager.showBalances();
        System.out.println();

        // ===============================
        // 3️⃣ Exact Split Example
        // Bob paid 1000
        // Alice owes 400
        // Charlie owes 600
        // ===============================

        List<Split> exactSplits = new ArrayList<>();
        exactSplits.add(new ExactSplit(manager.getUser("U1"), 400));
        exactSplits.add(new ExactSplit(manager.getUser("U3"), 600));

        manager.addExpense(
                "E2",
                "U2",
                1000,
                SplitType.EXACT,
                exactSplits
        );

        System.out.println("After Exact Split Expense:");
        manager.showBalances();
        System.out.println();

        // ===============================
        // 4️⃣ Percentage Split Example
        // Charlie paid 1000
        // Alice 50%
        // Bob 50%
        // ===============================

        List<Split> percentageSplits = new ArrayList<>();
        percentageSplits.add(new PercentageSplit(manager.getUser("U1"), 50));
        percentageSplits.add(new PercentageSplit(manager.getUser("U2"), 50));

        manager.addExpense(
                "E3",
                "U3",
                1000,
                SplitType.PERCENTAGE,
                percentageSplits
        );

        System.out.println("After Percentage Split Expense:");
        manager.showBalances();
        System.out.println();

        // ===============================
        // 5️⃣ Settle Up
        // Bob settles 200 with Alice
        // ===============================

        manager.settleUp("U2", "U1", 200);

        System.out.println("After Bob Settles 200 with Alice:");
        manager.showBalances();
    }
}