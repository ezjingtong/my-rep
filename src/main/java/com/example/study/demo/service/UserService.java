package com.example.study.demo.service;

import com.example.study.demo.pojo.Result;
import com.example.study.demo.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService {
    void register(String username, String password) throws Exception;

    String login(String username, String password);

    User getAllByUsername(String username);

    void update(User user);

    void updateAvater(String userPic);

    void updatePwd(Map<String, String> pwd);
}
