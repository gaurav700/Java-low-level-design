package com.pm.InterfaceSegregationPrinciple.impl;

import com.pm.InterfaceSegregationPrinciple.AdminControls;
import com.pm.InterfaceSegregationPrinciple.entity.Role;
import com.pm.InterfaceSegregationPrinciple.entity.User;

public class AdminUserService implements AdminControls {
    BasicUserService basicUserService;

    public AdminUserService(BasicUserService basicUserService) {
        this.basicUserService = basicUserService;
    }

    @Override
    public void banUser(String userId) {
        User user = basicUserService.getUser(userId);
        if(user.getRole() == Role.NORMAL_USER){
            throw new IllegalArgumentException("User is already banned");
        }
        user.setRole(Role.NORMAL_USER);
    }

    @Override
    public void promoteUse(String userid, Role role) {
        User user = basicUserService.getUser(userid);
        user.setRole(role);
    }
}
