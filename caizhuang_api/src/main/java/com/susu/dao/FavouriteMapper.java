package com.susu.dao;

import com.susu.entity.Favourite;
import com.susu.entity.FavouriteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FavouriteMapper {
    long countByExample(FavouriteExample example);

    int deleteByExample(FavouriteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Favourite record);
    int insertByCart(@Param("userId") int userId,
                     @Param("goodsId") int goodsId);

    int insertSelective(Favourite record);

    List<Favourite> selectByExample(FavouriteExample example);

    Favourite selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Favourite record, @Param("example") FavouriteExample example);

    int updateByExample(@Param("record") Favourite record, @Param("example") FavouriteExample example);

    int updateByPrimaryKeySelective(Favourite record);

    int updateByPrimaryKey(Favourite record);

    Integer selectByUidAndPid(Favourite favourite);
    Integer selectByUidAndPid1(@Param("userId") int userId,
                               @Param("goodsId") int goodsId);

    void delLike(Favourite favourite);

    void delete(Favourite favourite);

    List<Favourite> showLike(int userId);

    void clearLike(int userId);

    void deleteByBatch(String[] d);

    List<Favourite> likeSearch(@Param("userId")int userId,
                               @Param("searchname")String searchname);
}