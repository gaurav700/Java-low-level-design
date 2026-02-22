package com.pm.strategy.impl;

import com.pm.strategy.BorrowPolicy;

public class PremiumUserPolicy implements BorrowPolicy {

    private static final int MAX_LIMIT = 10;

    @Override
    public boolean canBorrow(int currentBorrowedCount) {
        return currentBorrowedCount < MAX_LIMIT;
    }

    @Override
    public int getMaxLimit() {
        return MAX_LIMIT;
    }
}