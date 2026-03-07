package com.pm.InterfaceSegregationPrinciple;

import java.util.List;

public interface AuditLog {
    List<String> getLoginHistory();
    List<String> getActivityLog();
}
