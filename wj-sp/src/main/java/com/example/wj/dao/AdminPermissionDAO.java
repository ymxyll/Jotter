package com.example.wj.dao;

import com.example.wj.entity.AdminPermission;
import com.example.wj.entity.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPermissionDAO extends JpaRepository<AdminPermission, Integer> {
    AdminRolePermission findById(int id);
}
