package com.susu.dao;

import com.susu.entity.User;
import com.susu.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(@Param("userName")String userName,
               @Param("password")String password);
    void insertToken(@Param("userId")Integer userId, @Param("token")String token);

    User getUserInfo(int id);

    void updateImg(User user);

    void updateInfo(User user);

    void updatePsw(User user);

    String getPsw(int id);
}