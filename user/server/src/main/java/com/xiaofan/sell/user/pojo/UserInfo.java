package com.xiaofan.sell.user.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private String id;

    private String username;

    private String password;

    private String openid;

    private int role;

    private Date createTime;

    private Date updateTime;

}