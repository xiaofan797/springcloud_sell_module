package com.xiaofan.sell.order.utils;


import com.xiaofan.sell.order.vo.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(data);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
