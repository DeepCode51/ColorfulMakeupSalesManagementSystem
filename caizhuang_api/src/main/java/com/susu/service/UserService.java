package com.susu.service;

import com.susu.entity.Customer;
import com.susu.entity.User;

public interface UserService {
    User getUser(String userName, String password);
    void insertToken(Integer userId, String token);


    User getUserInfo(int id);

    void updateImg(User user);

    void updateInfo(User user);

    void updatePsw(User user);

    String getPsw(int id);
}
