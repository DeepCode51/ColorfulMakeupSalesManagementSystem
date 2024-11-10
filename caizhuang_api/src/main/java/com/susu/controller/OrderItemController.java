package com.susu.controller;

import com.susu.entity.BgOrder;
import com.susu.entity.Msg;
import com.susu.entity.OrderItem;
import com.susu.service.BgOrderService;
import com.susu.service.OrderItemService;
import com.susu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BgOrderService bgOrderService;

    @RequestMapping("/updateItemStatus")
    public Msg updateItemStatus(@RequestBody OrderItem orderItem){
        orderItemService.updateItemStatus(orderItem);
        return Msg.success();
    }

    //判断是否全部评价
    @RequestMapping("/isAllReview")
    public Msg isAllReview(@RequestBody OrderItem orderItem){
        int count=orderItemService.selectCount(orderItem);
        int count1=orderItemService.selectStatusCount(orderItem);
        System.out.println(count);
        System.out.println(count1);
        if(count==count1){
            System.out.println("hhhhhhwfherfreuffnnccnfn");
            orderService.updateOrderStatus(orderItem.getOrderId());
            //当订单明细都被评价之后，该订单状态改为5 表示已评价 同时改变后台订单的的状态改为5，表前台买家已评价
            BgOrder bgOrder=new BgOrder();
            bgOrder.setOrderId(orderItem.getOrderId());
            bgOrder.setStatus("5");
            bgOrderService.updateStatus(bgOrder);
        }else{
            //不做任何操作
        }
        return Msg.success();
    }


}
