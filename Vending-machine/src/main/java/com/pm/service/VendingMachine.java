package com.pm.service;

import com.pm.entity.Product;
import com.pm.entity.enums.Denomination;
import com.pm.entity.state.State;
import com.pm.entity.state.impl.DispenseState;
import com.pm.entity.state.impl.HasMoneyState;
import com.pm.entity.state.impl.IdleState;
import com.pm.entity.state.impl.OutOfStockState;

public class VendingMachine {

    private final Inventory inventory;
    private final CashInventory cashInventory;

    // States
    private final State idleState;
    private final State hasMoneyState;
    private final State dispenseState;
    private final State outOfStockState;

    private State currentState;

    // Transaction Data
    private int currentBalance;
    private Product selectedProduct;

    public VendingMachine() {

        this.inventory = new Inventory();
        this.cashInventory = new CashInventory();

        this.idleState = new IdleState(this);
        this.hasMoneyState = new HasMoneyState(this);
        this.dispenseState = new DispenseState(this);
        this.outOfStockState = new OutOfStockState(this);

        this.currentState = idleState;

        this.currentBalance = 0;
        this.selectedProduct = null;
    }

    // =============================
    // Public APIs (Delegated to State)
    // =============================

    public void insertMoney(Denomination denomination) {
        currentState.insertMoney(denomination);
    }

    public void selectProduct(String productId) {
        currentState.selectProduct(productId);
    }

    public void dispense() {
        currentState.dispense();
    }

    public void cancel() {
        currentState.cancel();
    }

    // =============================
    // State Getters
    // =============================

    public State getIdleState() {
        return idleState;
    }

    public State getHasMoneyState() {
        return hasMoneyState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public State getOutOfStockState() {
        return outOfStockState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    // =============================
    // Inventory Access
    // =============================

    public Inventory getInventory() {
        return inventory;
    }

    public CashInventory getCashInventory() {
        return cashInventory;
    }

    // =============================
    // Transaction Helpers
    // =============================

    public void addBalance(int amount) {
        currentBalance += amount;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void resetTransaction() {
        currentBalance = 0;
        selectedProduct = null;
    }
}