package com.susu.service;

import com.susu.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer getUser(String userName, String password);

    Customer getUserInfo(int id);

    void updateImg(Customer user);

    void updateInfo(Customer user);

    String getPsw(int id);

    void updatePsw(Customer user);

    void insertUser(Customer user);

    boolean count(String userName);

    void insertToken(Integer id, String token);

    void setMiQues(Customer user);

    List<Customer> getAlluser();

//    void Login(String userName, String password);



    void updatePswQuestion(String username,String passwordQuestion);
}
