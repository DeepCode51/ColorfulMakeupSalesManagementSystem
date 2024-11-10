package com.susu.controller;


import com.susu.entity.Msg;
import com.susu.entity.User;

import com.susu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController//返回json数据
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    @CrossOrigin
    public Msg login(String userName, String password){
        User user=userService.getUser(userName,password);
        if(user != null){
            long time=System.currentTimeMillis();
            String token=time+userName;
            userService.insertToken(user.getUserId(),token);
            user.setToken(token);
            return Msg.success().add("user",user);
        }else{
            return Msg.fail();
        }

    }


    @RequestMapping("/getUserInfo")
    public  Msg getUserInfo(@RequestBody User user){
        int id=user.getUserId();
        User user1=userService.getUserInfo(id);
        return Msg.success().add("user",user1);
    }

    //上传头像并保存到本地 上传可以公用
    @RequestMapping("/upload")
    public Msg upload(MultipartFile file, User user, ModelMap map)throws IOException {
        //图片上传成功后，将图片的地址写到数据库
        //E:\idea\workspace\hf\img
        String filePath = "D:\\shop\\img";//保存图片的路径
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
//        System.out.println(originalFilename);
        //新的文件名字
//        String newFileName = UUID.randomUUID()+originalFilename;
        String newFileName = UUID.randomUUID()+".jpg";//不要加原始文件名称了
//        System.out.println(newFileName);
        //封装上传文件位置的全路径
        File targetFile = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
//        user.setImg(newFileName);
        return Msg.success().add("imgData",newFileName);
    }

    //更新数据库的头像名称
    @RequestMapping("/updateImg")
    public Msg updateImg(@RequestBody User user){
        int id=user.getUserId();
        String img=user.getImg();
        userService.updateImg(user);
        return Msg.success();

    }


    //更新用户信息
    @RequestMapping("/updateInfo")
    public  Msg updateInfo(@RequestBody User user){
        int id=user.getUserId();
        String nickName=user.getNickName();
        String phone=user.getPhone();
        int gender=user.getGender();
        userService.updateInfo(user);
        return Msg.success();

    }

    //修改密码
    @RequestMapping("/updatePsw")
    public  Msg updatePsw(@RequestBody User user, HttpServletRequest request)throws ServletException, IOException{

        int id=user.getUserId();
        String passPast=userService.getPsw(id);
        System.out.println("胡二虎额司法"+passPast);
//        String oldPsw=request.getParameter("oldPsw");
        String oldPsw=user.getOldPsw();
        System.out.println("wawdjafjooo噢噢噢噢"+oldPsw);
        if(oldPsw.equals(passPast)){
            String password=user.getPassword();
            userService.updatePsw(user);
            return Msg.success();

        }else{
            return Msg.fail();
        }

    }


}
