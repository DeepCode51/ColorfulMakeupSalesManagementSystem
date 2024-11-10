package com.susu.service.impl;

import com.susu.dao.CartMapper;
import com.susu.dao.FavouriteMapper;
import com.susu.entity.Cart;
import com.susu.entity.Favourite;
import com.susu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private FavouriteMapper favouriteMapper;

    @Override
    public void addCart(Cart cart) {
        Cart cart1=cartMapper.getCartByUidAndPid(cart);
        Integer quantity=cart.getQuantity();
        if(cart1 != null){
            cart1.setQuantity(quantity+cart1.getQuantity());
            cartMapper.update(cart1);
        }else{
            cartMapper.insert(cart);
        }

    }

    @Override
    public List<Cart> showCart(int userId) {
        return cartMapper.showCart(userId);
    }

    @Override
    public void updateCart(Cart cart) {
        cartMapper.update(cart);
    }

    @Override
    public void deleteOne(int id) {
        cartMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void clearCart(int userId) {
        cartMapper.clearCart(userId);
    }

    @Override
    public void deleteByBatch(String[] d) {
        cartMapper.deleteByBatch(d);
    }

    //移入到收藏夹
    @Override
    public void cartToLike(Cart cart) {
        Integer count= favouriteMapper.selectByUidAndPid1(cart.getUserId(),cart.getGoodsId());
        System.out.println("count的值为"+count);
        if(count ==0){
            favouriteMapper.insertByCart(cart.getUserId(),cart.getGoodsId());
            cartMapper.deleteByPrimaryKey(cart.getId());
        }else{
            cartMapper.deleteByPrimaryKey(cart.getId());
        }
    }


}
