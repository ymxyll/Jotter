package com.example.wj.service;

import com.example.wj.dao.UserDAO;
import com.example.wj.dto.UserDTO;
import com.example.wj.entity.AdminRole;
import com.example.wj.entity.User;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    public boolean isExist(String username) {
        User user = userDAO.findByUsername(username);
        return null != user;
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password) {
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public int register(User user) {
        String username = user.getUsername();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
        String password = user.getPassword();

        //将转义字符转化为HTML实体字符，传入前端显示时不出错
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        phone = HtmlUtils.htmlEscape(phone);
        user.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        user.setEmail(email);

        if(username.equals("") || password.equals("")) {
            return 0;   //表示由于用户名为空或密码为空导致注册失败
        }

        boolean exist = isExist(username);

        if(exist) {
            return 2;   //表示由于用户名重复需要用户再选一个用户名
        }

        //密码加密模块
        //默认生成16位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;  //执行几次加密算法
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();

        user.setSalt(salt); //加密用的盐也保存到数据库中
        user.setPassword(encodedPassword);

        userDAO.save(user);

        return 1;   //1代表注册成功
    }

    public void updateUserStatus(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        userInDB.setEnabled(user.isEnabled());
        userDAO.save(userInDB);
    }

    public User resetPassword(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        userInDB.setSalt(salt);
        int times = 2;  //执行几次加密算法
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDB.setPassword(encodedPassword);
        return userDAO.save(userInDB);
    }


    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    //查出所有数据并转化为视图类
    public List<UserDTO> list(){
        List<User> users = userDAO.findAll();


        List<UserDTO> userDTOS = users
                .stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());

        userDTOS.forEach(u -> {
            List<AdminRole> roles = adminRoleService.listRolesByUser(u.getUsername());
            u.setRoles(roles);
        });

        return userDTOS;
    }

    public void editUser(User user) {
        User userInDB = userDAO.findByUsername(user.getUsername());
        if(user.getUsername() != userInDB.getUsername()) {
            userInDB.setUsername(user.getUsername());
        }
        if(user.getEmail() != userInDB.getEmail()) {
            userInDB.setEmail(user.getEmail());
        }
        if(!Objects.equals(user.getPhone(), userInDB.getPhone())) {
            userInDB.setPhone(user.getPhone());
        }
        if(user.getRoles() != null && !Objects.equals(user.getRoles(), userInDB.getRoles()))
        {
            adminUserRoleService.saveRoleChanges(userInDB.getId(), user.getRoles());
        }
        userDAO.save(userInDB);
    }
}
