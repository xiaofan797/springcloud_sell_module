package com.xiaofan.sell.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW((byte)0, "新订单"),
    FINISHED((byte)1, "完结"),
    CANCEL((byte)2, "取消"),
    ;
    private Byte code;

    private String message;

    OrderStatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
