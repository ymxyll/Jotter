package com.example.wj.service;

import com.example.wj.dao.AdminRolePermissionDAO;
import com.example.wj.entity.AdminPermission;
import com.example.wj.entity.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRolePermissionService {
    @Autowired
    AdminRolePermissionDAO adminRolePermissionDAO;

    List<AdminRolePermission> findAllByRid(int rid){
        return adminRolePermissionDAO.findAllByRid(rid);
    }

    @Transactional
    //更改权限对应页面
    public void savePermChanges(int rid, List<AdminPermission> permissions) {
        adminRolePermissionDAO.deleteAllByRid(rid);
        List<AdminRolePermission> rps = new ArrayList<>();
        permissions.forEach(p -> {
            AdminRolePermission rp = new AdminRolePermission();
            rp.setRid(rid);
            rp.setPid(p.getId());
            rps.add(rp);
        });
        adminRolePermissionDAO.saveAll(rps);
    }
}
