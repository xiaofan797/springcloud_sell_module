package com.xiaofan.sell.order.vo;
import lombok.Data;

/**
 * 返回的VO对象
 * @param <T>
 */
@Data
public class ResultVO<T> {

    private int code;

    private String msg;

    private T data;

}
