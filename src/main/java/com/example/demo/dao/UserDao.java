package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.CId;
import com.example.demo.entity.CString;
import com.example.demo.entity.User;

public interface UserDao {
    List<User> login(User user);

    List<User> findByName(CString username);

    int register(User user);

    int modUser(User user);

    int delUser(CId id);
}
