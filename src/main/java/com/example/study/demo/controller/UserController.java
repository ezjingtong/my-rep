package com.example.study.demo.controller;


import com.example.study.demo.pojo.Result;
import com.example.study.demo.pojo.User;
import com.example.study.demo.service.UserService;

import com.example.study.demo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLStreamHandlerFactory;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    private UserService userService;


    /**
     * 注册用户
     * @param username
     * @param password
     * @return
     */
    @PostMapping ("/register")
    public Result register(String username, String password) throws Exception {
        userService.register(username,password);
        return Result.success();
    }

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result<String>login(String username,String password){
        String login = userService.login(username, password);
        return Result.success(login);
    }


    /**
     * 根据用户名来查询用户数据
     * @param
     * @return
     */
    @GetMapping("/userInfo")
    public Result<User>userInfo(){
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User allByUsername = userService.getAllByUsername(username);
        return Result.success(allByUsername);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result<User>updateUser(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }


    /**
     * 修改用户头像
     * @param userPic
     * @return
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvater(@RequestParam String userPic){
        userService.updateAvater(userPic);
        return Result.success();
    }

    /**
     * 修改用户密码
     * @param pwd
     * @return
     */
    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody Map<String,String>pwd){
        userService.updatePwd(pwd);
        return Result.success();
    }


}
