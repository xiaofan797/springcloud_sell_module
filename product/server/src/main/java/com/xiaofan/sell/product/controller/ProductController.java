package com.xiaofan.sell.product.controller;

import com.xiaofan.sell.product.common.DecreaseStockInput;
import com.xiaofan.sell.product.common.ProductInfoOutput;
import com.xiaofan.sell.product.dto.CartDTO;
import com.xiaofan.sell.product.pojo.ProductCategory;
import com.xiaofan.sell.product.pojo.ProductInfo;
import com.xiaofan.sell.product.service.ProductCategoryService;
import com.xiaofan.sell.product.service.ProductInfoService;
import com.xiaofan.sell.product.utils.ResultVOUtil;
import com.xiaofan.sell.product.vo.ProductInfoVo;
import com.xiaofan.sell.product.vo.ProductVO;
import com.xiaofan.sell.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 查找商品信息
     * @return
     */
    @GetMapping("/list")
    public ResultVO list(){
        //1.获取所有的分类信息
        List<ProductCategory> productCategoryList = productCategoryService.findList(new ProductCategory());
        List<Integer> typesList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList) {
            typesList.add(productCategory.getCategoryType());
        }
        //2.获取上述分类下的上架商品
        List<ProductInfo> productInfoList = productInfoService.findByTypes(typesList);
        //3.封装数据
        List<ProductVO> productVOList = new ArrayList<>();//data
        for (ProductCategory productCategory:productCategoryList) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(productCategory,productVO);
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productVO.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVos.add(productInfoVo);
                }
            }
            productVO.setProductInfoVoList(productInfoVos);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
    /**
     *  查找商品信息
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList){
      /*  try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        List<ProductInfo> productInfoList = productInfoService.findByProductIds(productIdList);
         List<ProductInfoOutput> productInfoOutputList = new ArrayList<>();
        for (ProductInfo productInfo:productInfoList) {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(productInfo,productInfoOutput);
            productInfoOutputList.add(productInfoOutput);
        }
        return productInfoOutputList;
    }
    /**
     * 扣减库存
     */
    @PostMapping("/decrStock")
    public String decrStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
         productInfoService.decrStock(decreaseStockInputList);
         return "ok";
    }

}
