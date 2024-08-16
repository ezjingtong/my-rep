package com.example.study.demo.service.impl;


import com.example.study.demo.exception.NumberException;
import com.example.study.demo.exception.UserException;
import com.example.study.demo.mapper.UserMapper;
import com.example.study.demo.pojo.User;
import com.example.study.demo.service.UserService;
import com.example.study.demo.utils.JwtUtil;
import com.example.study.demo.utils.Md5Util;
import com.example.study.demo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param username
     * @param password
     */
    public void register(String username, String password) throws Exception {
        if (username.length()>4&&username.length()<16
           &&password.length()>4&&password.length()<16
        ){
            User userAll = userMapper.selectByUsername(username);
            if (userAll == null) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(Md5Util.getMD5String(password));
                user.setCreateTime(LocalDateTime.now());
                user.setUpdateTime(LocalDateTime.now());
                userMapper.insert(user);
            }else {
                throw new UserException("用户已经存在");
            }
        }else {
            throw new NumberException("用户名或者密码个数不对");
        }


    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public String login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (Md5Util.getMD5String(password).equals(user.getPassword())){
            //jwt令牌验证
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            return JwtUtil.genToken(claims);
        }else {
            throw new RuntimeException("用户名或者密码错误");
        }
    }

    /**
     * 根据用户名来查询用户数据
     * @param
     * @return
     */
    public User getAllByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        return user;
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void update(User user) {
        Map<String,Object> map = ThreadLocalUtil.get();
        int id = (int) map.get("id");
        user.setId(id);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    /**
     * 修改用户头像地址
     * @param userPic
     */
    @Override
    public void updateAvater(String userPic) {
        Map map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvater(userPic,id);
    }

    /**
     * 修改密码
     * @param pwd
     */
    @Override
    public void updatePwd(Map<String, String> pwd) {
        String old_pwd = pwd.get("old_pwd");
        String new_pwd = pwd.get("new_pwd");
        String re_pwd = pwd.get("re_pwd");

        if (old_pwd.length()==0||new_pwd.length()==0||re_pwd.length()==0){
            throw  new RuntimeException("缺少指定参数");
        }
        Map map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userMapper.selectByUsername(username);
        String password = user.getPassword();
        String md5String = Md5Util.getMD5String(old_pwd);
        if (password.equals(old_pwd)){
            if (new_pwd.equals(md5String)){
                user.setPassword(new_pwd);
                userMapper.updatePwd(user);
            }else {
                throw new RuntimeException("密码两次不一样");
            }
        }else {
            throw new RuntimeException("旧密码错误");
        }
    }


}
