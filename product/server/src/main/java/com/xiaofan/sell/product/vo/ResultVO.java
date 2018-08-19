package com.xiaofan.sell.product.vo;
import lombok.Data;

import java.util.List;

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
