package com.susu.service;

import com.susu.entity.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> getGoodsType();

    void addGoodsType(GoodsType goodsType);

    void editGoodsType(GoodsType goodsType);

    void delType(int id);

    GoodsType getTypeById(int id);

    boolean count(String goodstypename);

    void deleteByBatch(String[] ids);
}
