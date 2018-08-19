package com.xiaofan.sell.product.vo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 类目VO
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;//分类名称

    @JsonProperty("type")
    private Integer categoryType;//类型编码

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;//商品列表
}
