package com.susu.controller;

import com.susu.entity.Cart;

import com.susu.entity.Favourite;
import com.susu.entity.Msg;
import com.susu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    //添加商品到购物车
    @RequestMapping("/addCart")
    public Msg addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        return Msg.success();
//        "userId":1,
//        "goodsId":2,
//        "quantity":9，
//         "size":""
    }
    //查看购物车
    @RequestMapping("/showCart")
    public  Msg showCart(@RequestBody Cart cart){
        int userId=cart.getUserId();
        List<Cart> cartList=cartService.showCart(userId);
        return Msg.success().add("cartList",cartList);
//        userId:1
    }

    //修改购物车的数量
    @RequestMapping("/updateCart")
    public Msg updateCart(@RequestBody Cart cart){
        cartService.updateCart(cart);
        return  Msg.success();
//        "id":3,
//        "quantity":9
    }
    //删除购物车的某个商品
    @RequestMapping("/deleteOne")
    public Msg deleteOne(@RequestBody Cart cart){
        int id=cart.getId();
        cartService.deleteOne(id);
        return Msg.success();
//        "id":3
    }

    //清空购物车
    @RequestMapping("/clearCart")
    public  Msg clearCart(@RequestBody Cart cart){
        int userId=cart.getUserId();
        cartService.clearCart(userId);
        return Msg.success();
//        "userId":1
    }

    //批量删除购物车的商品
    @RequestMapping("/deleteByBatch")
    public  Msg deleteByBatch(String ids){
        System.out.println(ids);
        String[] d=ids.split(",");//把数组里的值逗号隔开
        cartService.deleteByBatch(d);
        return Msg.success();
//        ?ids=4,5
    }

    //将商品移到收藏夹 现在有的参数是：购物车ID，商品ID，用户ID，商品规格
    //将商品ID，用户ID插入到收藏夹中 如果存在重复就不进行任何操作 ，于此同时，在购物车中删除此个购物车ID，
    @RequestMapping("cartToLike")
    public Msg cartToLike(@RequestBody Cart cart){
        cartService.cartToLike(cart);
        return Msg.success();
    }

    ////将商品批量移到收藏夹 现在有的参数是：购物车ID，商品ID，用户ID，商品规格
    //  //将商品ID，用户ID插入到收藏夹中 如果存在重复就不进行任何操作 ，于此同时，在购物车中删除此个购物车ID，
//    @RequestMapping("cartToLikeByBatch")
//    public Msg cartToLike(@RequestBody Cart cart,){
//        cartService.cartToLikeByBatch(cart);
//        return Msg.success();
//    }




}
