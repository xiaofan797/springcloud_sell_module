package com.xiaofan.sell.user.exceptions;

import lombok.Getter;


@Getter
public class UserException extends RuntimeException{
    private Integer code;
}
