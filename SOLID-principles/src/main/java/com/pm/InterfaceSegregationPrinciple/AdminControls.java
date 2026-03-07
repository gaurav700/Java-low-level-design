package com.pm.InterfaceSegregationPrinciple;

import com.pm.InterfaceSegregationPrinciple.entity.Role;

public interface AdminControls {
    void banUser(String userId);
    void promoteUse(String userid, Role role);
}
