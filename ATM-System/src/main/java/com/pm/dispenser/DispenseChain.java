package com.pm.dispenser;

public interface DispenseChain {
    void dispense(int amount);
    void setNextChain(DispenseChain nextChain);
    boolean canDispense(int amount);
}
