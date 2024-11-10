package com.susu.controller;

import com.susu.entity.Customer;
import com.susu.entity.Msg;
import com.susu.service.CustomerService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //登录
    @RequestMapping("/login")
    public Msg login(String userName, String password){
        Customer user=customerService.getUser(userName,password);

        if(user != null){
            if (user.getPasswordQuestion().equals("2")){
                return Msg.fail().add("message","您的帐号已被管理员封禁，请联系管理员解封");
            }
            long time=System.currentTimeMillis();
            String token=time+userName;
            customerService.insertToken(user.getId(),token);
            user.setToken(token);
            return Msg.success().add("user",user);
        }else{
            return Msg.fail().add("message","输入正确的用户名或密码");
        }

    }

    //获取用户个人信息(没用到  前端获取localstorage）
    @RequestMapping("/getUserInfo")
    public  Msg getUserInfo(@RequestBody Customer user){
        int id=user.getId();
        Customer user1=customerService.getUserInfo(id);
        return Msg.success().add("user",user1);
    }
    //设置密保
    @RequestMapping("/setMiQues")
    public Msg setMiQues(@RequestBody Customer user){
        customerService.setMiQues(user);
        return  Msg.success();
    }

    //更新数据库的头像名称
    @RequestMapping("/updateImg")
    public Msg updateImg(@RequestBody Customer user){
        int id=user.getId();
        String img=user.getImg();
        customerService.updateImg(user);
        return Msg.success();
    }

    //更新用户信息
    @RequestMapping("/updateInfo")
    public  Msg updateInfo(@RequestBody Customer user,HttpServletRequest request) throws UnsupportedEncodingException {

       request.setCharacterEncoding("utf-8");

        user.setRealName("帅哥");
        customerService.updateInfo(user);


        return Msg.success();

//        JSONArray a = new JSONArray(); // shujuku
//        JSONArray b = new JSONArray(); // fanhuide
//        f
//        l
//        for(1-7){
//
//sql =             if(riqi == l){
//
//      for() {
//
//      }if(b. 有没有日期=l){
//              JSONObject jsonObject = new JSONObject();
//              jsonObject.put()
//              b.put(jsonObject)
//
//          }else{
//              b.getJSONObject()
//          }
//      }
//            }
//        }
//
   }


    //修改密码
    @RequestMapping("/updatePsw")
    public  Msg updatePsw(@RequestBody Customer user, HttpServletRequest request)throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");

        int id=user.getId();
        String passPast=customerService.getPsw(id);

        System.out.println("胡二虎额司法"+passPast);

        String oldPsw=user.getOldPsw();

        System.out.println("wawdjafjooo噢噢噢噢"+oldPsw);

        if(oldPsw.equals(passPast)){
            String password=user.getPassword();
            customerService.updatePsw(user);
            return Msg.success();

        }else{
            return Msg.fail();
        }

    }

    //注册用户，注册用户的同时判断用户名是否存在。
    @RequestMapping("/insertUser")
    public Msg insertUser(@RequestBody Customer user){
        boolean a=customerService.count(user.getUserName());
        if(a){
            customerService.insertUser(user);
            return Msg.success();
        }else{
            return Msg.fail();
        }

    }

    //注册用户的同时判断用户名是否存在(前端添加判断，焦点移出事件)
    @RequestMapping("/count")
    public Msg count(@RequestBody Customer user){
        boolean a=customerService.count(user.getUserName());
        if(a){
            return Msg.success().add("success","无重复用户名");
        }else{
            return Msg.fail().add("error","重复用户名");
        }
    }

    //查询所有用户
    @RequestMapping("/getAlluser")
    public Msg getAlluser(){
        List<Customer> customerList=customerService.getAlluser();
        return Msg.success().add("customerList",customerList);
    }

    //更新用户权限（密保）
    @RequestMapping("/updatePswQuestion")
    public Msg updatePswQuestion(String username,String passwordQuestion){
        customerService.updatePswQuestion(username,passwordQuestion);
        return Msg.success();
    }




}
