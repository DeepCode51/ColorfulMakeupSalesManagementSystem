package com.susu.service;

import com.susu.entity.Goods;
import com.susu.entity.GoodsType;

import java.util.List;

public interface GoodService {
    List<Goods> getList();

    Goods getProductById(int id);

    void updateProduct(Goods goods);

    void addProduct(Goods goods);

    void delProduct(int id);

    void updateSale(Goods goods);

    List<Goods> getSearch(String searchname,Integer typeId,Integer hasSale);

    List<Goods> getListByType(int id);

    void uploadImg(Goods goods);

    void deleteByBatch(String[] d);

    List<Goods> getsearchGoods(String searchname, Integer typeId, Integer maxPrice, Integer minPrice);

    void updateRemrk(Goods goods);

    Integer getCount(int id);

    /*List<Goods> getlistByShopId(int id);

    List<Goods> getlistBySearch(Goods goods);

    List<Goods> getlistBySearch(int shopId, String goodsname);

    List<Goods> getSearch(String searchName);*/
}
