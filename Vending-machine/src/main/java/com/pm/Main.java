package com.pm;

import com.pm.entity.enums.Denomination;
import com.pm.service.Inventory;
import com.pm.service.VendingMachine;

public class Main {

    public static void main(String[] args) {

        // 1️⃣ Create vending machine
        VendingMachine machine = new VendingMachine();

        // 2️⃣ Add products to inventory
        Inventory inventory = machine.getInventory();
        String productId1 = inventory.addProduct("Coke", 15, 5);     // price = 15
        String productId2 = inventory.addProduct("Pepsi", 10, 3);
        String productId3 = inventory.addProduct("Chips", 20, 2);

        // 3️⃣ Preload machine with change
        machine.getCashInventory().addMoney(Denomination.TEN);
        machine.getCashInventory().addMoney(Denomination.FIVE);
        machine.getCashInventory().addMoney(Denomination.ONE);
        machine.getCashInventory().addMoney(Denomination.ONE);
        machine.getCashInventory().addMoney(Denomination.ONE);
        machine.getCashInventory().addMoney(Denomination.ONE);
        machine.getCashInventory().addMoney(Denomination.ONE);

        try {

            // 4️⃣ User inserts money
            machine.insertMoney(Denomination.TEN);
            machine.insertMoney(Denomination.FIVE);

            // 5️⃣ User selects product
            // Replace with actual product ID printed during addProduct
            machine.selectProduct(productId1);

            // 6️⃣ Dispense
            machine.dispense();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            machine.cancel();
        }
    }
}