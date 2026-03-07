package com.pm.InterfaceSegregationPrinciple;

import com.pm.InterfaceSegregationPrinciple.entity.Role;
import com.pm.InterfaceSegregationPrinciple.entity.User;

public interface UserCrud {
    String CreateUser(String name, String email, Role role);
    void UpdateUser(String userId, String newEmail, Role role);
    void DeleteUser(String userId);
    User getUser(String userId);
}
