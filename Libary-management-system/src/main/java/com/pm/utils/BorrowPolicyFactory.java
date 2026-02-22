package com.pm.utils;

import com.pm.entity.Role;
import com.pm.strategy.BorrowPolicy;
import com.pm.strategy.impl.PremiumUserPolicy;
import com.pm.strategy.impl.RegularUserPolicy;

public class BorrowPolicyFactory {

    public static BorrowPolicy createPolicy(Role role) {
        switch (role) {
            case USER_NORMAL:
                return new RegularUserPolicy();
            case USER_PREMIUM:
                return new PremiumUserPolicy();
            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }
}