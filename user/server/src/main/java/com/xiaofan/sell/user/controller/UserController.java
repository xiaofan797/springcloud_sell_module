package com.xiaofan.sell.user.controller;

import com.xiaofan.sell.user.constants.RedisConstants;
import com.xiaofan.sell.user.enums.RoleEnums;
import com.xiaofan.sell.user.pojo.UserInfo;
import com.xiaofan.sell.user.service.UserService;
import com.xiaofan.sell.user.utils.CookieUtils;
import com.xiaofan.sell.user.utils.ResultVOUtil;
import com.xiaofan.sell.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
     * 买家登录
     * @param openid
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyerLogin(@RequestParam("openid")String openid, HttpServletResponse response){
        //查询用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid(openid);
        List<UserInfo> list = userService.findList(userInfo);
        //用户登录失败
        if(list.size()!=1){
            return ResultVOUtil.error(null);
        }
        UserInfo userInfoFound = list.get(0);
        if(userInfoFound.getRole()!= RoleEnums.BUYER.getCode()){
            return ResultVOUtil.error(null);//角色异常
        }
        //用户登录成功，设置cookie
        CookieUtils.setCookie(response,"openid",openid);
        return ResultVOUtil.success(list.get(0).getId());
    }

    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO sellerLogin(@RequestParam("openid") String openid, HttpServletRequest request,
                                HttpServletResponse response){
        String token = CookieUtils.getCookieValue(request, "token");

        if(token!=null){//从redis中查找
            String key = String.format(RedisConstants.TOKEN_TEMPLATE,token);
            String value = stringRedisTemplate.opsForValue().get(key);
            if(StringUtils.isEmpty(value)){
                return ResultVOUtil.error(null);
            }
            //之前已经登录过了
            return ResultVOUtil.success(null);
        }
        //查询用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid(openid);
        List<UserInfo> list = userService.findList(userInfo);
        //用户登录失败
        if(list.size()!=1){
            return ResultVOUtil.error(null);
        }
        UserInfo userInfoFound = list.get(0);
        if(userInfoFound.getRole()!= RoleEnums.SELLER.getCode()){
            return ResultVOUtil.error(null);//角色异常
        }
        //用户登录成功，设置redis
        String uuid = UUID.randomUUID().toString();
        String key = String.format(RedisConstants.TOKEN_TEMPLATE, uuid);
        stringRedisTemplate.opsForValue().set(key,openid);

        //设置cookie
        CookieUtils.setCookie(response,"token",uuid);
        return ResultVOUtil.success(null);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}
