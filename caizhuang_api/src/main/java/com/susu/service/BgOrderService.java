package com.susu.service;

import com.susu.entity.BgOrder;

import java.util.Date;
import java.util.List;

public interface BgOrderService {
    void toBgOrder(BgOrder bgOrder);

    List<BgOrder> showOrder(String orderId, String status,Integer goodstypeId);

    BgOrder getOrderDetail(BgOrder bgOrder);

    void updateStatus(BgOrder bgOrder);

    List<BgOrder> timeOrder(Date startTime, Date endTime);

    int deleteBgOrderByPrimaryKey(String orderId);
}
