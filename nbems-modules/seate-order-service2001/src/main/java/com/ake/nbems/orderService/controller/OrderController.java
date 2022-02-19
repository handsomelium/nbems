package com.ake.nbems.orderService.controller;

import com.ake.nbems.orderService.domain.CommonResult;
import com.ake.nbems.orderService.domain.Order;
import com.ake.nbems.orderService.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lyrstart
 * @create 2021-12-13 14:16
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;


    @GetMapping("/create")

    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }

}
