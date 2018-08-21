package com.xiaofan.sell.user.utils;

import com.xiaofan.sell.user.vo.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(data);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO error(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setData(data);
        resultVO.setMsg("失败");
        return resultVO;
    }
}
