package com.xiaofan.sell.order.message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaofan.sell.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductReceiver {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    /**
     * 监控库存变化
     */
    @RabbitListener(queuesToDeclare = @Queue("productStockInfos"))
    public void processStockChange(String message) {
        log.info("processStockChange={}",message);
        Gson gs = new Gson();
        List<ProductInfoOutput> productInfoOutputList = gs.fromJson(message, new TypeToken<List<ProductInfoOutput>>() {
        }.getType());//把JSON格式的字符串转为List
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }
    }

}
