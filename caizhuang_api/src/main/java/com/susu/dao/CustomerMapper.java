package com.susu.dao;

import com.susu.entity.Customer;
import com.susu.entity.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    long countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    Customer getUser(@Param("userName") String userName,
                     @Param("password")String password);

    void updateImg(Customer user);

    void updateInfo(Customer user);

    String getPsw(int id);

    void updatePsw(Customer user);

    void insertUser(Customer user);

    int count(String userName);

    void insertToken(@Param("id") Integer id, @Param("token") String token);

    void setMiQues(Customer user);

    List<Customer> getAlluser();

    void updatePswQuestion(@Param("userName") String userName,@Param("passwordQuestion") String passwordQuestion);
}
