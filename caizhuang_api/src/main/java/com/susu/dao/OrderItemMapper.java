package com.susu.dao;

import com.susu.entity.Cart;
import com.susu.entity.OrderItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {

    int deleteByPrimaryKey(Integer itemId);
    int insert(OrderItem record);
    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer itemId);
    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    void cartToOrderItem(List<OrderItem> list);

    void toOrderItem(@Param("orderId")String orderId,
                     @Param("cart")List<Cart> cartList);

    void toOneItem(@Param("orderId")String orderId, @Param("list")List<OrderItem> list);

    List<OrderItem> getOrderItem(String orderId);

    int selectCount(OrderItem orderItem);

    int selectStatusCount(OrderItem orderItem);
}