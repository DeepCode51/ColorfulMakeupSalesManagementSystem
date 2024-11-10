package com.susu.dao;

import com.susu.entity.Goods;
import com.susu.entity.GoodsExample;
import java.util.List;

import com.susu.entity.GoodsType;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    void updateProduct(Goods goods);

    void addProduct(Goods goods);

    void updateSale(Goods goods);

    List<Goods> getSearch(@Param("searchname")String searchname,
                          @Param("typeId")Integer typeId,
                          @Param("hasSale")Integer hasSale);


    List<Goods> getListByType(int id);

    void uploadImg(Goods goods);

    void deleteByBatch(String[] d);

    List<Goods> getsearchGoods(@Param("searchname")String searchname,
                               @Param("typeId")Integer typeId,
                               @Param("maxPrice")Integer maxPrice,
                               @Param("minPrice")Integer minPrice);

    void updateRemrk(Goods goods);

    Integer getCount(@Param("typeId")int typeId);
}