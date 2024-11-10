package com.susu.service.impl;

import com.susu.dao.OrderItemMapper;
import com.susu.dao.OrderMapper;
import com.susu.entity.Order;
import com.susu.entity.OrderItem;
import com.susu.service.OrderItemService;
import com.susu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;


    @Override
    public void cartToOrderItem(List<OrderItem> list) {
          orderItemMapper.cartToOrderItem(list);
    }

    @Override
    public List<OrderItem> getOrderItem(String orderId) {
        return orderItemMapper.getOrderItem(orderId);
    }

    @Override
    public void updateItemStatus(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKey(orderItem);
    }

    @Override
    public int selectCount(OrderItem orderItem) {
        return orderItemMapper.selectCount(orderItem);
    }

    @Override
    public int selectStatusCount(OrderItem orderItem) {
        return orderItemMapper.selectStatusCount(orderItem);
    }


}
