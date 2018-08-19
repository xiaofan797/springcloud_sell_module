package com.xiaofan.sell.product.dao;

import com.xiaofan.sell.product.pojo.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductInfoMapper {
    int deleteByPrimaryKey(String productId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo findById(String productId);

    List<ProductInfo> findList(@Param("typesList") List<Integer> typesList,//分类ID批量查询
                                  @Param("productIdList") List<String> productIdList,//产品ID批量查询
                                  @Param("product")ProductInfo productInfo);//产品条件

    int update(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}