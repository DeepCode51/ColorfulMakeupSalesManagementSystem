package com.susu.controller;
import com.alibaba.fastjson.JSON;
import com.susu.entity.Cart;
import com.susu.entity.Msg;
import com.susu.entity.Order;
import com.susu.entity.OrderItem;
import com.susu.service.OrderItemService;
import com.susu.service.OrderService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;


    //生成订单信息（前台）
    @RequestMapping("/cartToOrder")
// value=”参数名”,required=”true/false”,defaultValue=””
    public Msg cartToOrder(@RequestBody Order order,
                           @RequestParam(value="ids",required=false,defaultValue="moren")String ids,
                           @RequestParam(value="item",required=false)String item){
        System.out.println(ids);
        String[] d=ids.split(",");
        System.out.println("d的值是"+d);
        System.out.println("d的值是"+ Arrays.toString(d));
        String orderId=orderService.cartToOrder(order,d,item);
        return Msg.success().add("orderId",orderId);
//        "userId":1,
//        "addId":1,
//        "totalprice":122,
//        "deliverymethod":"hsda",
//        "memo":"asdas",
//        "paymethod":"1",
//        "status":0
    }
    //修改订单的状态，生成订单之后将订单状态改为1，表示已付款
    @RequestMapping("payOver")
    public  Msg payOver(@RequestBody Order order){
        orderService.payOver(order);
        //付款成功之后，将这个订单的所有信息作为参数插入到后台的订单信息中
        Order order1=orderService.showOrderDetail(order);
        List<OrderItem> orderItemList=orderItemService.getOrderItem(order1.getOrderId());
        order1.setOrderItemList(orderItemList);
        return Msg.success().add("order",order1);
    }


    //查看订单（前台）
    @RequestMapping("showOrder")
    public  Msg showOrder(@RequestBody Order order){

        List<Order> orderList=orderService.showOrder(order);
        for(Order o:orderList){
            System.out.println(o.getOrderId());
            String orderId=o.getOrderId();
            List<OrderItem> orderItemList=orderItemService.getOrderItem(orderId);
            System.out.println(orderItemList);
            o.setOrderItemList(orderItemList);
        }
        return Msg.success().add("orderList",orderList);
    }

    //查看订单详情
    @RequestMapping("showOrderDetail")
    public Msg showOrderDetail(@RequestBody Order order){
        Order order1=orderService.showOrderDetail(order);
        List<OrderItem> orderItemList=orderItemService.getOrderItem(order1.getOrderId());
        order1.setOrderItemList(orderItemList);
        return Msg.success().add("order",order1);
    }

    //修改订单的状态！！！！！取消订单status的状态变为4
    @RequestMapping("updateStatus")
    public Msg updateStatus(@RequestBody Order order){
        orderService.updateStatus(order);
        return Msg.success();
    }

    //根据orderId删除订单
    @RequestMapping("deleteOrder")
    public Msg deleteOrder(@RequestParam(value="orderId",required=false) String orderId){
        System.out.println("删除订单入参----------》"+orderId);
        int i = orderService.deleteByPrimaryKey(orderId);
        System.out.println("删除返回的结果---------》"+i);
        return Msg.success();
    }

}
