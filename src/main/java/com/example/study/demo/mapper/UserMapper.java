package com.example.study.demo.mapper;

import com.example.study.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {


    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

    @Insert("insert into user (username, password,create_time,update_time) values (#{username},#{password},#{createTime},#{updateTime})")
    void insert(User user);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    User update(User user);

    @Update("update user set user_pic=#{userPic},update_time=now() where id=#{id}")
    void updateAvater(String userPic, Integer id);

    @Update("update user set password=#{password},update_time=now() where id=#{id}")
    void updatePwd(User user);
}
