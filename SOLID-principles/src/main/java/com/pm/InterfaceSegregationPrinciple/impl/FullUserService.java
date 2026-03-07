package com.pm.InterfaceSegregationPrinciple.impl;

import com.pm.InterfaceSegregationPrinciple.AdminControls;
import com.pm.InterfaceSegregationPrinciple.AuditLog;
import com.pm.InterfaceSegregationPrinciple.UserCrud;
import com.pm.InterfaceSegregationPrinciple.entity.Role;
import com.pm.InterfaceSegregationPrinciple.entity.User;

import java.util.List;

public class FullUserService implements UserCrud, AdminControls, AuditLog {

    AdminUserService  adminUserService;
    BasicUserService  basicUserService;

    public FullUserService(AdminUserService adminUserService, BasicUserService basicUserService) {
        this.adminUserService = adminUserService;
        this.basicUserService = basicUserService;
    }

    @Override
    public void banUser(String userId) {
        adminUserService.banUser(userId);
    }

    @Override
    public void promoteUse(String userid, Role role) {
        adminUserService.promoteUse(userid, role);
    }

    @Override
    public String CreateUser(String name, String email, Role role) {
        return basicUserService.CreateUser(name, email, role);
    }

    @Override
    public void UpdateUser(String userId, String newEmail, Role role) {
        basicUserService.UpdateUser(userId, newEmail, role);
    }

    @Override
    public void DeleteUser(String userId) {
        basicUserService.DeleteUser(userId);
    }

    @Override
    public User getUser(String userId) {
        return basicUserService.getUser(userId);
    }

    @Override
    public List<String> getLoginHistory() {
        return List.of();
    }

    @Override
    public List<String> getActivityLog() {
        return List.of();
    }
}
