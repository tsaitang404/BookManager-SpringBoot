package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.CString;
import com.example.demo.entity.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        List<User> logins = userDao.login(user);
        if (logins.size() == 1) {
            return logins.get(0);
        } else {
            return null;
        }
    }

    public CString cString = new CString();

    // 注册接口
    // 参数 user对象 返回 uid 成功 0 or null 失败
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        cString.setText(user.getUsername());
        if (userDao.findByName(cString).isEmpty()) {
            userDao.register(user);

            List<User> res = userDao.findByName(cString);
            if (!res.isEmpty())
                return res.get(0);
            else
                return null;
        } else {
            return null;

        }
    }

    @PostMapping("/findbyname")
    public User postMethodName(@RequestBody CString username) {
        username.setText(username.getText().toLowerCase());
        List<User> res = userDao.findByName(username);
        if (!res.isEmpty())
            return res.get(0);
        else
            return null;
    }

}
