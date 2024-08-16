package com.example.study.demo.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;//主键ID

    private String username;//用户名

    @JsonIgnore//springboot 将字段转化为json格式时自动忽略

    private String password;//密码

    private String nickname;//昵称

    private String email;//邮箱

    private String userPic;//用户头像地址

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间
}
