package com.xiaofan.sell.product.common;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 2017-12-09 21:23
 */
@Data
public class ProductInfoOutput  {

    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Byte productStatus;

    /** 类目编号. */
    private Integer categoryType;
}
