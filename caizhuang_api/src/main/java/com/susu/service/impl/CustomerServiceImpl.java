package com.susu.service.impl;

import com.susu.dao.CustomerMapper;
import com.susu.entity.Customer;
import com.susu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer getUser(String userName, String password) {
        return customerMapper.getUser(userName,password);
    }

    @Override
    public Customer getUserInfo(int id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateImg(Customer user) {
        customerMapper.updateImg(user);
    }

    @Override
    public void updateInfo(Customer user) {

        System.out.println("我是service层："+user.getRealName());
        customerMapper.updateInfo(user);
    }

    @Override
    public String getPsw(int id) {
        return customerMapper.getPsw(id);
    }

    @Override
    public void updatePsw(Customer user) {
        customerMapper.updatePsw(user);
    }

    @Override
    public void insertUser(Customer user) {
        customerMapper.insertUser(user);
    }

    @Override
    public boolean count(String userName) {
        int count=customerMapper.count(userName);
        return count==0;
    }

    @Override
    public void insertToken(Integer id, String token) {
        customerMapper.insertToken(id,token);
    }

    @Override
    public void setMiQues(Customer user) {
        customerMapper.setMiQues(user);
    }

    @Override
    public List<Customer> getAlluser() {
        return customerMapper.getAlluser();
    }

    @Override
    public void updatePswQuestion(String username, String passwordQuestion) {
        customerMapper.updatePswQuestion(username,passwordQuestion);
    }

   /* @Override
    public void Login(String userName, String password) {

    }*/
}
