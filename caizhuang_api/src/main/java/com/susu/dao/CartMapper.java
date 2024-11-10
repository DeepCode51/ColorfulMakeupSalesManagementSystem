package com.susu.dao;

import com.susu.entity.Cart;
import com.susu.entity.CartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {
    long countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart getCartByUidAndPid(Cart cart);

    void update(Cart cart);

    List<Cart> showCart(int userId);

    void clearCart(int userId);

    void deleteByBatch(String[] d);

//    SELECT c.* ,g.sell_price FROM tb_cart c LEFT JOIN tb_goods g ON c.goods_id=g.id WHERE c.id IN(10,11)
    List<Cart> getCartListByIds(String[] d);
}