package com.xiaofan.sell.user.service;

import com.xiaofan.sell.user.pojo.UserInfo;

import java.util.List;

public interface UserService {

    List<UserInfo> findList(UserInfo userInfo);
}
