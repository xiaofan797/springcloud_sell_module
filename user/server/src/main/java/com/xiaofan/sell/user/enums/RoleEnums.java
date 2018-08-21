package com.xiaofan.sell.user.enums;

import lombok.Getter;

/**
 * 角色枚举类
 */
@Getter
public enum RoleEnums {
    BUYER(1,"买家"),
    SELLER(2,"卖家"),
    ;

    private Integer code;

    private String message;

    RoleEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
