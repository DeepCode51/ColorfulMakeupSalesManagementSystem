package com.susu.dao;

import com.susu.entity.BgOrderItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BgOrderItemMapper {
    int deleteByPrimaryKey(Integer itemId);
    int insert(BgOrderItem record);
    int insertSelective(BgOrderItem record);
    BgOrderItem selectByPrimaryKey(Integer itemId);
    int updateByPrimaryKeySelective(BgOrderItem record);
    int updateByPrimaryKey(BgOrderItem record);

    void toOrderItem(@Param("orderId")String orderId,
                     @Param("list")List<BgOrderItem> bgOrderItemList);

    List<BgOrderItem> getOrderItem(String orderId);
}