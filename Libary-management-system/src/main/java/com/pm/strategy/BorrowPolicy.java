package com.pm.strategy;

public interface BorrowPolicy {
    boolean canBorrow(int currentBorrowedCount);
    int getMaxLimit();
}
