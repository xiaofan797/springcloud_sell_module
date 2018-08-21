package com.xiaofan.sell.user.service.impl;

import com.xiaofan.sell.user.dao.UserInfoMapper;
import com.xiaofan.sell.user.pojo.UserInfo;
import com.xiaofan.sell.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> findList(UserInfo userInfo) {
        return userInfoMapper.findList(userInfo);
    }
}
