package com.xiaofan.sell.product.fallback;


import com.xiaofan.sell.product.client.ProductClient;
import com.xiaofan.sell.product.common.DecreaseStockInput;
import com.xiaofan.sell.product.common.ProductInfoOutput;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
        return null;
    }

    @Override
    public String decrStock(List<DecreaseStockInput> cartDTOList) {
        return null;
    }
}
