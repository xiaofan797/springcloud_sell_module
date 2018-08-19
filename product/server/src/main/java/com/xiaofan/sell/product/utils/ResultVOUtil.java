package com.xiaofan.sell.product.utils;


import com.xiaofan.sell.product.vo.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(data);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
