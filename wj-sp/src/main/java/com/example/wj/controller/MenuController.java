package com.example.wj.controller;

import com.example.wj.result.Result;
import com.example.wj.result.ResultFactory;
import com.example.wj.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    @Autowired
    AdminMenuService adminMenuService;

    @GetMapping("/api/menu")
    public Result menu() {  //根据用户名得到当前用户能访问的页面
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByCurrentUser());
    }

    @GetMapping("/api/admin/role/menu")
    public Result listAllMenus() {  //得到所有页面
        return ResultFactory.buildSuccessResult(adminMenuService.getMenusByRoleId(1));
    }
}
