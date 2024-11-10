package com.susu.service;

import com.susu.entity.Cart;

import java.util.List;

public interface CartService {
    void addCart(Cart cart);

    List<Cart> showCart(int id);

    void updateCart(Cart cart);

    void deleteOne(int id);

    void clearCart(int userId);

    void deleteByBatch(String[] d);

    void cartToLike(Cart cart);
}
