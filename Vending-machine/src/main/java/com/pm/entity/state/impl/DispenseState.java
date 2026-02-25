package com.pm.entity.state.impl;

import com.pm.entity.Product;
import com.pm.entity.enums.Denomination;
import com.pm.entity.state.State;
import com.pm.service.VendingMachine;

import java.util.Map;

public class DispenseState implements State {

    private final VendingMachine machine;

    public DispenseState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(Denomination denomination) {
        throw new RuntimeException("Dispensing in progress");
    }

    @Override
    public void selectProduct(String productId) {
        throw new RuntimeException("Already selected product");
    }

    @Override
    public void dispense() {

        Product product = machine.getSelectedProduct();

        // Deduct product
        machine.getInventory().deduct(product.getId());

        int changeAmount = (int) (machine.getCurrentBalance() - product.getPrice());

        Map<Denomination, Integer> change =
                machine.getCashInventory().calculateChange(changeAmount);

        System.out.println("Dispensed product: " + product.getName());
        System.out.println("Change returned: " + change);

        machine.resetTransaction();
        machine.setState(machine.getIdleState());
    }

    @Override
    public void cancel() {
        throw new RuntimeException("Cannot cancel while dispensing");
    }
}