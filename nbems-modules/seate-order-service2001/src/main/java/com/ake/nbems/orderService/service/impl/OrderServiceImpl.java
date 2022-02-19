package com.ake.nbems.orderService.service.impl;


import com.ake.nbems.orderService.dao.OrderDao;
import com.ake.nbems.orderService.domain.Order;
import com.ake.nbems.orderService.service.OrderService;
import com.ake.nbems.orderService.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lyrstart
 * @create 2021-12-13 14:11
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {

        System.out.println("新建订单");
        //1 新建订单
        orderDao.create(order);

        //2 扣减库存
        System.out.println("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        System.out.println("----->订单微服务开始调用库存，做扣减end");


        System.out.println("修改订单状态");
        //4 修改订单状态，从零到1,1代表已经完成
        orderDao.update(order.getUserId(),0);


    }
}
