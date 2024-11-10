package com.susu.dao;

import com.susu.entity.Order;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {


    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);


    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> showOrder(Order order);

    Order showOrderDetail(Order order);

    void updateStatus(Order order);

    void updateOrderStatus(String orderId);
}