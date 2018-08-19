package com.xiaofan.sell.product.service.impl;

import com.google.gson.Gson;
import com.xiaofan.sell.product.common.DecreaseStockInput;
import com.xiaofan.sell.product.common.ProductInfoOutput;
import com.xiaofan.sell.product.dao.ProductInfoMapper;
import com.xiaofan.sell.product.enums.ProductStatusEnum;
import com.xiaofan.sell.product.enums.ResultEnum;
import com.xiaofan.sell.product.exception.ProductException;
import com.xiaofan.sell.product.pojo.ProductInfo;
import com.xiaofan.sell.product.service.ProductInfoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<ProductInfo> findByTypes(List<Integer> typesList) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoMapper.findList(typesList,null,productInfo);
    }

    @Override
    public List<ProductInfo> findByProductIds(List<String> productIdList) {
        return productInfoMapper.findList(null,productIdList,new ProductInfo());
    }

    @Override
    public void decrStock(List<DecreaseStockInput> decreaseStockInputList){
        //扣减库存
        List<ProductInfoOutput> productInfoOutputList = decrStockHandle(decreaseStockInputList);
        //发送消息
        Gson gs = new Gson();
        rabbitTemplate.convertAndSend("productStockInfos",gs.toJson(productInfoOutputList));
    }


    @Transactional
    public List<ProductInfoOutput> decrStockHandle(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfoOutput> productInfoOutputList = new ArrayList<>();
        for(DecreaseStockInput decreaseStockInput:decreaseStockInputList){
            ProductInfo productInfo = productInfoMapper.findById(decreaseStockInput.getProductId());
            if(productInfo==null){//商品不存在
                throw  new ProductException(ResultEnum.PRODUCT_NOT_EXIST.getCode(),
                        ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }
            Integer nowStock = productInfo.getProductStock()-decreaseStockInput.getProductQuantity();
            if (nowStock<0){
                throw  new ProductException(ResultEnum.STOCK_ERROR.getCode(),
                        ResultEnum.STOCK_ERROR.getMessage());
            }
            productInfo.setProductStock(nowStock);
            productInfoMapper.update(productInfo);
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(productInfo,productInfoOutput);
            productInfoOutputList.add(productInfoOutput);
        }
        return productInfoOutputList;
    }
}
