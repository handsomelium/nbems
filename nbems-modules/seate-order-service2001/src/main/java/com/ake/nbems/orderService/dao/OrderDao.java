package com.ake.nbems.orderService.dao;


import com.ake.nbems.orderService.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lyrstart
 * @create 2021-12-13 14:00
 */
@Mapper
public interface OrderDao {
//    1 新建订单
    void create(Order order);

    //2 修改订单状态，从零改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
