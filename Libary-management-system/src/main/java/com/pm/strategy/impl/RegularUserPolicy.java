package com.pm.strategy.impl;

import com.pm.strategy.BorrowPolicy;

public class RegularUserPolicy implements BorrowPolicy {

    private static final int MAX_LIMIT = 5;

    @Override
    public boolean canBorrow(int currentBorrowedCount) {
        return currentBorrowedCount <  MAX_LIMIT;
    }

    @Override
    public int getMaxLimit() {
        return MAX_LIMIT;
    }
}
