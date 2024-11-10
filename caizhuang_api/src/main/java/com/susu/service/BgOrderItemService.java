package com.susu.service;

import com.susu.entity.BgOrderItem;

import java.util.List;

public interface BgOrderItemService {
    List<BgOrderItem> getOrderItem(String orderId);
}
