package com.xiaofan.sell.order.controller;

import com.xiaofan.sell.order.converter.OrderForm2OrderDTOConverter;
import com.xiaofan.sell.order.dto.OrderDTO;
import com.xiaofan.sell.order.enums.OrderStatusEnum;
import com.xiaofan.sell.order.enums.ResultEnum;
import com.xiaofan.sell.order.exception.OrderException;
import com.xiaofan.sell.order.form.OrderForm;
import com.xiaofan.sell.order.pojo.OrderMaster;
import com.xiaofan.sell.order.service.OrderService;
import com.xiaofan.sell.order.utils.ResultVOUtil;
import com.xiaofan.sell.order.vo.ResultVO;
import com.xiaofan.sell.product.client.ProductClient;
import com.xiaofan.sell.product.common.DecreaseStockInput;
import com.xiaofan.sell.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult){
        //1.参数校验
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        //2.创建订单
        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @PostMapping("/finish")
    public ResultVO finish(String orderId){
        //判断订单号是否为空
        if(StringUtils.isEmpty(orderId)){
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        OrderMaster orderMaster = orderService.finishOrder(orderId);
        return ResultVOUtil.success(orderMaster);
    }
}
