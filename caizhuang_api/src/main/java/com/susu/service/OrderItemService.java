package com.susu.service;

import com.susu.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    void cartToOrderItem(List<OrderItem> list);

    List<OrderItem> getOrderItem(String orderId);

    void updateItemStatus(OrderItem orderItem);

    int selectCount(OrderItem orderItem);

    int selectStatusCount(OrderItem orderItem);
}
