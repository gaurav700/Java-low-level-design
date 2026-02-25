package com.pm.entity.state.impl;

import com.pm.entity.Product;
import com.pm.entity.enums.Denomination;
import com.pm.entity.state.State;
import com.pm.service.VendingMachine;

public class HasMoneyState implements State {

    private final VendingMachine machine;

    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(Denomination denomination) {
        machine.getCashInventory().addMoney(denomination);
        machine.addBalance(denomination.getValue());
        System.out.println("Money inserted: " + denomination.getValue());
    }

    @Override
    public void selectProduct(String productId) {

        if (!machine.getInventory().isAvailable(productId)) {
            machine.setState(machine.getOutOfStockState());
            throw new RuntimeException("Product out of stock");
        }

        Product product = machine.getInventory().getProduct(productId);

        if (machine.getCurrentBalance() < product.getPrice()) {
            throw new RuntimeException("Insufficient funds");
        }

        machine.setSelectedProduct(product);
        machine.setState(machine.getDispenseState());
    }

    @Override
    public void dispense() {
        throw new RuntimeException("Select product first");
    }

    @Override
    public void cancel() {
        int refund = machine.getCurrentBalance();
        machine.resetTransaction();
        machine.setState(machine.getIdleState());
        System.out.println("Refunded: " + refund);
    }
}