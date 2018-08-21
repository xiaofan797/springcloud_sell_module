package com.xiaofan.sell.user.service.impl;

import com.xiaofan.sell.user.pojo.UserInfo;
import com.xiaofan.sell.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest  {

    @Autowired
    UserService userService;

    @Test
    public void findList() {
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid("222");
        List<UserInfo> list = userService.findList(userInfo);
        log.info("list={}",list);
    }
}