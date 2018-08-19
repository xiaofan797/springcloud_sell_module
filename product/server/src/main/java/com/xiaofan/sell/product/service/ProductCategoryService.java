package com.xiaofan.sell.product.service;

import com.xiaofan.sell.product.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    public List<ProductCategory> findList(ProductCategory productCategory);

}
