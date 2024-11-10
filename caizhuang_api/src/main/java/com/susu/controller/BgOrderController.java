package com.susu.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.susu.entity.BgOrder;
import com.susu.entity.BgOrderItem;
import com.susu.entity.Msg;
import com.susu.service.BgOrderItemService;
import com.susu.service.BgOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bgorder")
public class BgOrderController {
    @Autowired
    private BgOrderService bgOrderService;

    @Autowired
    private BgOrderItemService bgOrderItemService;

    //付款之后 将订单插入到商家的订单管理中
    @RequestMapping("toBgOrder")
    public Msg toBgOrder(@RequestBody BgOrder bgOrder){
        bgOrderService.toBgOrder(bgOrder);
        return Msg.success();
    }
    //查看订单 ，条件查询 订单编号 以及订单的状态，分页
    @RequestMapping("showOrder")
    public Msg showOrder(int pageNum,int pageSize,Integer goodstypeId,String orderId,String status){
        PageHelper.startPage(pageNum,pageSize);
        List<BgOrder> bgOrderList=bgOrderService.showOrder(orderId,status,goodstypeId);
        PageInfo<BgOrder> pageInfo=new PageInfo<BgOrder>(bgOrderList);
        return Msg.success().add("pageInfo",pageInfo);
    }

    //更加订单id查看订单详情
    @RequestMapping("getOrderDetail")
    public Msg getOrderDetail(@RequestBody BgOrder bgOrder){
        BgOrder bgOrder1=bgOrderService.getOrderDetail(bgOrder);
        List<BgOrderItem> bgOrderItemList=bgOrderItemService.getOrderItem(bgOrder1.getOrderId());
        bgOrder1.setBgOrderItemList(bgOrderItemList);
        return Msg.success().add("bgOrder",bgOrder1);
    }

    //根据订单id，该变status 默认status==1表已付款代发货  status==2表示已发货 status==3 表示已收货 status==4订单关闭（这是由商家进行关闭）status==7 表示已退货
    @RequestMapping("/updateStatus")
    public Msg updateStatus(@RequestBody BgOrder bgOrder){
        bgOrderService.updateStatus(bgOrder);
        return Msg.success();
    }


    //根据订单创建的时间，starttime endtime 查看每一天订单的总数和订单的金额
    @RequestMapping("/timeOrder")
    public Msg timeOrder (Date startTime,Date endTime){
        List<BgOrder> bgOrderList=bgOrderService.timeOrder(startTime,endTime);
        return Msg.success().add("bgOrderList",bgOrderList);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
    //根据orderId删除订单
    @RequestMapping("deleteOrder")
    public Msg deleteOrder(@RequestParam(value="orderId",required=false) String orderId){
        System.out.println("删除订单入参----------》"+orderId);
        int i = bgOrderService.deleteBgOrderByPrimaryKey(orderId);
        System.out.println("删除返回的结果---------》"+i);
        return Msg.success();
    }
}
