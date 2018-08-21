package com.xiaofan.sell.order.service;

import com.xiaofan.sell.order.dto.OrderDTO;
import com.xiaofan.sell.order.pojo.OrderMaster;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);

    OrderMaster finishOrder(String orderId);
}
