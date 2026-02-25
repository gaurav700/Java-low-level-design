package com.pm.service;

import com.pm.entity.enums.Denomination;

import java.util.*;

public class CashInventory {

    private final Map<Denomination, Integer> cashInventory = new HashMap<>();

    public CashInventory() {
        for (Denomination denomination : Denomination.values()) {
            cashInventory.put(denomination, 0);
        }
    }

    // Add money into machine
    public void addMoney(Denomination denomination) {
        cashInventory.put(
                denomination,
                cashInventory.getOrDefault(denomination, 0) + 1
        );
    }

    // Used internally after successful change calculation
    private void deductMoney(Denomination denomination, int count) {
        cashInventory.put(
                denomination,
                cashInventory.get(denomination) - count
        );
    }

    // Check if machine has sufficient change (without modifying inventory)
    public boolean hasSufficientChange(int amount) {
        try {
            simulateChangeCalculation(amount);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    // Public method to calculate and deduct change
    public Map<Denomination, Integer> calculateChange(int amount) {
        Map<Denomination, Integer> changeMap = simulateChangeCalculation(amount);

        // Deduct only after successful simulation
        for (Map.Entry<Denomination, Integer> entry : changeMap.entrySet()) {
            deductMoney(entry.getKey(), entry.getValue());
        }

        return changeMap;
    }

    // Core greedy logic (does NOT modify real inventory)
    private Map<Denomination, Integer> simulateChangeCalculation(int amount) {

        Map<Denomination, Integer> tempInventory = new HashMap<>(cashInventory);
        Map<Denomination, Integer> changeToReturn = new HashMap<>();

        // Sort denominations high to low
        List<Denomination> sortedDenominations =
                new ArrayList<>(Arrays.asList(Denomination.values()));

        sortedDenominations.sort(
                (d1, d2) -> d2.getValue() - d1.getValue()
        );

        for (Denomination denomination : sortedDenominations) {

            int denomValue = denomination.getValue();
            int availableCount = tempInventory.getOrDefault(denomination, 0);

            while (amount >= denomValue && availableCount > 0) {
                amount -= denomValue;
                availableCount--;

                changeToReturn.put(
                        denomination,
                        changeToReturn.getOrDefault(denomination, 0) + 1
                );
            }
        }

        if (amount != 0) {
            throw new RuntimeException("Insufficient change available");
        }

        return changeToReturn;
    }

    // Utility method for debugging
    public void printInventory() {
        System.out.println("Cash Inventory:");
        for (Map.Entry<Denomination, Integer> entry : cashInventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}