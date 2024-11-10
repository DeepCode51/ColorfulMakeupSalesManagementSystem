package com.susu.service;

import com.susu.entity.Order;

import java.util.List;

public interface OrderService {
    String cartToOrder(Order order,String[] d,String item);

    List<Order> showOrder(Order order);

    void payOver(Order order);

    Order showOrderDetail(Order order);

    void updateStatus(Order order);

    void updateOrderStatus(String orderId);

    int deleteByPrimaryKey(String orderId);
}
