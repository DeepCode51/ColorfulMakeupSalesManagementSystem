package com.susu.dao;

import com.susu.entity.BgOrder;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BgOrderMapper {
    int deleteBgOrderByPrimaryKey(String orderId);

    int insert(BgOrder record);

    int insertSelective(BgOrder record);

    BgOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(BgOrder record);

    int updateByPrimaryKey(BgOrder record);

    List<BgOrder> showOrder(@Param("orderId")String orderId, @Param("status")String status,@Param("goodstypeId")Integer goodstypeId);

    BgOrder getOrderDetail(BgOrder bgOrder);

    void updateStatus(BgOrder bgOrder);

    List<BgOrder> timeOrder(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
}