package com.pm.exception;

public class InsufficientFundsException extends ATMException {
    public InsufficientFundsException() {
        super("Insufficient funds in account.");
    }
}