package com.xiaofan.sell.user.utils;

import com.xiaofan.sell.user.constants.CookieConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static void setCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(CookieConstants.EXPIRE_TIME);
        response.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie != null) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
