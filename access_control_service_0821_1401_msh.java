// 代码生成时间: 2025-08-21 14:01:19
package com.example.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class AccessControlService {

    // 该方法检查用户是否具有管理员权限
    @PreAuthorize("hasRole('ADMIN')")
    public void adminAccess() {
        // 管理员特定操作
        System.out.println("Admin access granted.");
    }

    // 该方法检查用户是否具有用户权限
    @PreAuthorize("hasRole('USER')")
    public void userAccess() {
        // 用户特定操作
        System.out.println("User access granted.");
    }

    // 该方法检查用户是否具有访问权限
    @PreAuthorize("isAuthenticated()")
    public void requireAuthentication() {
        // 需要认证的操作
        System.out.println("Authentication required.");
    }

    // 获取当前认证用户信息
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        } else {
            throw new IllegalStateException("No authentication context available.");
        }
    }
}
