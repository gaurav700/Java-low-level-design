package com.pm.entity.state.impl;

import com.pm.entity.enums.Denomination;
import com.pm.entity.state.State;
import com.pm.service.VendingMachine;

public class OutOfStockState implements State {

    private final VendingMachine machine;

    public OutOfStockState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(Denomination denomination) {
        throw new RuntimeException("Product out of stock");
    }

    @Override
    public void selectProduct(String productId) {
        throw new RuntimeException("Product unavailable");
    }

    @Override
    public void dispense() {
        throw new RuntimeException("Cannot dispense");
    }

    @Override
    public void cancel() {
        machine.resetTransaction();
        machine.setState(machine.getIdleState());
    }
}