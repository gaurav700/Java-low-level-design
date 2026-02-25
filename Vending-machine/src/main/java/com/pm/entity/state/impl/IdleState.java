package com.pm.entity.state.impl;

import com.pm.entity.enums.Denomination;
import com.pm.entity.state.State;
import com.pm.service.VendingMachine;

public class IdleState implements State {

    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(Denomination denomination) {
        machine.getCashInventory().addMoney(denomination);
        machine.addBalance(denomination.getValue());

        machine.setState(machine.getHasMoneyState());
        System.out.println("Money inserted: " + denomination.getValue());
    }


    @Override
    public void selectProduct(String productId) {
        throw new RuntimeException("Insert money first");
    }

    @Override
    public void dispense() {
        throw new RuntimeException("No product selected");
    }

    @Override
    public void cancel() {
        System.out.println("Nothing to cancel");
    }
}
