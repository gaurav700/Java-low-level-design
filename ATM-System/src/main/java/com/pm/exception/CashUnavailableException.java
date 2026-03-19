package com.pm.exception;

public class CashUnavailableException extends ATMException {
    public CashUnavailableException() {
        super("Cash is unavailable");
    }
}
