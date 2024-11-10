package com.susu.controller;


import com.susu.entity.Address;
import com.susu.entity.Msg;
import com.susu.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    //添加住址
    @RequestMapping("/addAddress")
    public Msg addAddress(@RequestBody Address address){
        String status=address.getStatus();
        String a="1";
        String b="0";
        //java中比较字符串用equals（）
        if(status.equals(b)){
            System.out.println("你执行的不");
            addressService.addAddress(address);
            return Msg.success();
        }
        if(status.equals(a)){
            System.out.println("五不知道的啊");
            addressService.setDeafult(address);
            addressService.addAddress(address);
            return Msg.success();
        }
        return Msg.fail();
//        "userId":1,
//        "address":"江苏省南京市宿城区"
    }

    //查看用户地址
    @RequestMapping("/showAddress")
    public Msg showAddress(@RequestBody Address address){
        int userId=address.getUserId();
        List<Address> addressList=addressService.showAddress(userId);
        return Msg.success().add("addressList",addressList);
//        userId:1
    }

    //修改住址
    @RequestMapping("/update")
    public Msg update(@RequestBody Address address){
        addressService.update(address);
        return Msg.success();
//        "id":2,
//        "address":"集散地分红施几点睡的是分工"
    }

    //删除一个地址
    @RequestMapping("/delete")
    public Msg delete(@RequestBody Address address){
        int id=address.getId();
        addressService.delete(id);
        return Msg.success();
//        "id":2
    }

    //批量删除
    @RequestMapping("/deleteByBatch")
    public Msg deleteByBatch(String ids){
        String[] d=ids.split(",");
        addressService.deleteByBatch(d);
        return Msg.success();
//        ?ids=1,3
    }

    //设置默认地址
    @RequestMapping("/setDeafult")
    public Msg setDeafult(@RequestBody Address address){
//        int userId=address.getUserId();
//        int id=address.getId();
        addressService.setDeafult(address);
        addressService.setStatus(address);
        return Msg.success();

    }

}
