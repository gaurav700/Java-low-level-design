package com.pm.exception;

public class InvalidPinException extends ATMException {
    public InvalidPinException() {
        super("Invalid PIN.");
    }
}