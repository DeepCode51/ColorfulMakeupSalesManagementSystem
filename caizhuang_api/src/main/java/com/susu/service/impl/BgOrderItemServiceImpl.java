package com.susu.service.impl;

import com.susu.dao.BgOrderItemMapper;
import com.susu.entity.BgOrderItem;
import com.susu.service.BgOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BgOrderItemServiceImpl implements BgOrderItemService {
    @Autowired
    private BgOrderItemMapper bgOrderItemMapper;

    @Override
    public List<BgOrderItem> getOrderItem(String orderId) {
        return bgOrderItemMapper.getOrderItem(orderId);
    }
}
