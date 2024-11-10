package com.susu.service.impl;

import com.alibaba.fastjson.JSON;
import com.susu.dao.BgOrderItemMapper;
import com.susu.dao.BgOrderMapper;
import com.susu.entity.BgOrder;
import com.susu.entity.BgOrderItem;
import com.susu.entity.OrderItem;
import com.susu.service.BgOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BgOrderServiceImpl implements BgOrderService {
    @Autowired
    private BgOrderMapper bgOrderMapper;
    @Autowired
    private BgOrderItemMapper bgOrderItemMapper;

    @Override
    public void toBgOrder(BgOrder bgOrder) {
        bgOrderMapper.insert(bgOrder);
        System.out.println("sdfhsfuewfew"+bgOrder.getItem());
        List<BgOrderItem> bgOrderItemList= JSON.parseArray(bgOrder.getItem(), BgOrderItem.class);
        System.out.println(bgOrderItemList.toString());
        String orderId=bgOrder.getOrderId();
        bgOrderItemMapper.toOrderItem(orderId,bgOrderItemList);

    }

    @Override
    public List<BgOrder> showOrder(String orderId, String status,Integer goodstypeId) {
        return bgOrderMapper.showOrder(orderId,status,goodstypeId);
    }

    @Override
    public BgOrder getOrderDetail(BgOrder bgOrder) {
        return bgOrderMapper.getOrderDetail(bgOrder);
    }

    @Override
    public void updateStatus(BgOrder bgOrder) {
        bgOrderMapper.updateStatus(bgOrder);
    }

    @Override
    public List<BgOrder> timeOrder(Date startTime, Date endTime) {
        return bgOrderMapper.timeOrder(startTime,endTime);
    }

    @Override
    public int deleteBgOrderByPrimaryKey(String orderId) {
        return bgOrderMapper.deleteBgOrderByPrimaryKey(orderId);
    }
}
