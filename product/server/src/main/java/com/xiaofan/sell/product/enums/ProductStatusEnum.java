package com.xiaofan.sell.product.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP((byte)0,"上架"),
    DOWN((byte)1,"下架"),
    ;
    private Byte code;
    private String message;

    ProductStatusEnum(Byte code, String message) {
        this.code = code;
        this.message = message;
    }
}
