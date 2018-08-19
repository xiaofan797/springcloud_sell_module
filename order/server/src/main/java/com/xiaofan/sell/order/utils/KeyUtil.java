package com.xiaofan.sell.order.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成订单号
     * @return
     */
    public static synchronized String generateKey(){
        Random random = new Random();
        int num = random.nextInt(900000)+100000;//6位随机数
        return System.currentTimeMillis()+String.valueOf(num);
    }

}
