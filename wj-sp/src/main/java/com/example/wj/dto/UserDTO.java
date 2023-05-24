package com.example.wj.dto;

import com.example.wj.dto.base.OutputConverter;
import com.example.wj.entity.AdminRole;
import com.example.wj.entity.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

//视图层
@Data
@ToString
public class UserDTO implements OutputConverter<UserDTO, User> {

    private int id;

    private String username;

    private String name;

    private String phone;

    private String email;

    private boolean enabled;

    private List<AdminRole> roles;

}
