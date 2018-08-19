package com.xiaofan.sell.product.client;

import com.xiaofan.sell.product.common.DecreaseStockInput;
import com.xiaofan.sell.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT")//要访问微服务的名称
public interface ProductClient {
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    /**
     * 扣减库存
     */
    @PostMapping("/product/decrStock")
    String decrStock(@RequestBody List<DecreaseStockInput> cartDTOList);

}
