package com.xiaofan.sell.product.service.impl;

import com.xiaofan.sell.product.dao.ProductCategoryMapper;
import com.xiaofan.sell.product.pojo.ProductCategory;
import com.xiaofan.sell.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> findList(ProductCategory productCategory) {
        return  productCategoryMapper.findList(productCategory);
    }
}
