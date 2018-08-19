package com.xiaofan.sell.product.service;

import com.xiaofan.sell.product.common.DecreaseStockInput;
import com.xiaofan.sell.product.pojo.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    List<ProductInfo> findByTypes(List<Integer> typesList);

    List<ProductInfo> findByProductIds(List<String> productIdList);

    void decrStock(List<DecreaseStockInput> decreaseStockInputList);
}
