package com.xiaofan.sell.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private String productId;
    private Integer productQuantity;
}
