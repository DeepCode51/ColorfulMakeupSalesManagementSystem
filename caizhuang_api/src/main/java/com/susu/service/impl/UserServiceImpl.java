package com.susu.service.impl;

import com.susu.dao.UserMapper;
import com.susu.entity.Customer;
import com.susu.entity.User;
import com.susu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUser(String userName, String password) {
        User user=userMapper.login(userName,password);
//        if(user!=null){
//            //登录后即执行修改token的操作
//            String token = getNewToken(System.currentTimeMillis() + "", user.getId());
//            if (userMapper.updateUserToken(user.getId(), token) > 0) {
//                //返回数据时带上token
//                user.setUserToken(token);
//                return user;
//            }
//        }
//        return null;
        return user;
    }
    @Override
    public void insertToken(Integer userId, String token) {

        userMapper.insertToken(userId,token);
    }

    @Override
    public User getUserInfo(int id) {
        return userMapper.getUserInfo(id);
    }

    @Override
    public void updateImg(User user) {
        userMapper.updateImg(user);
    }

    @Override
    public void updateInfo(User user) {
        userMapper.updateInfo(user);
    }

    @Override
    public void updatePsw(User user) {
        userMapper.updatePsw(user);
    }

    @Override
    public String getPsw(int id) {
        return userMapper.getPsw(id);
    }


}
