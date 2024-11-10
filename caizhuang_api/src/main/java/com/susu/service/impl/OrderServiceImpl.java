package com.susu.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.susu.dao.CartMapper;
import com.susu.dao.OrderItemMapper;
import com.susu.dao.OrderMapper;
import com.susu.entity.Cart;
import com.susu.entity.Order;
import com.susu.entity.OrderItem;
import com.susu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;



    @Override
    public String cartToOrder(Order order,String[] d,String item) {//,String ids
        Date now= new Date();//当前时间
        String orderId =createOrderId(now);//生成订单号
        order.setOrderId(orderId);//设置本订单的订单号
//        order.setCreateTime(now);//设置订单时间
        orderMapper.insert(order);//插入订单
        //根据提交订单的id，去购物车查询相应的list
        System.out.println("这一页面中的d"+d);
        String[] arr1=new String[]{"moren"};
        if(Arrays.equals(d,arr1)){
            System.out.println("zhixin的吗！！啊啊啊啊啊啊");
            //插入订单明细中，如果也给他一个list，这个list里面只会有一组数据，也遍历一下，插入数据库，虽然遍历了 但是只有一条
            System.out.println(item);
            List<OrderItem> list = JSON.parseArray(item, OrderItem.class);
            orderItemMapper.toOneItem(orderId,list);
//            orderItemMapper.toOrderItem(orderId,list);
        }else{
            List<Cart> cartList=cartMapper.getCartListByIds(d);
            System.out.println("cartList的值为"+cartList);
            //批量插入订单明细中
            orderItemMapper.toOrderItem(orderId,cartList);
            //删除购物车中的商品
            cartMapper.deleteByBatch(d);
            //改变商品相应的库存
        }
        return orderId;
       //UPDATE tb_goods SET stock = CASE id WHEN 1 THEN 300 WHEN 2 THEN 400 WHEN 3 THEN 500 END WHERE id IN(1,2,3)
    }

    @Override
    public List<Order> showOrder(Order order) {
        return orderMapper.showOrder(order);
    }

    @Override
    public void payOver(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public Order showOrderDetail(Order order) {
        return orderMapper.showOrderDetail(order);
    }

    @Override
    public void updateStatus(Order order) {
        orderMapper.updateStatus(order);
    }

    @Override
    public void updateOrderStatus(String orderId) {
        orderMapper.updateOrderStatus(orderId);
    }

    @Override
    public int deleteByPrimaryKey(String orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }


    //生成订单号
    private String createOrderId(Date now){
        StringBuffer sb= new StringBuffer();//空的stringBuffter
        //时间格式
        SimpleDateFormat s= new SimpleDateFormat("yyyyMMddHHmmssSS");
        String dateStr =s.format(now);//格式化当前时间
        sb.append(dateStr);//加入时间字符串
        for(int i=0;i<3;i++){//加入3位随机整数
            sb.append((int) (Math.random()*10));
        }
        return sb.toString();//转成String型返回
    }

}
