package com.susu.service.impl;

import com.susu.dao.GoodsMapper;
import com.susu.entity.Goods;
import com.susu.entity.GoodsType;
import com.susu.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getList() {
        return goodsMapper.selectByExampleWithBLOBs(null);
    }

    @Override
    public Goods getProductById(int id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateProduct(Goods goods) {
        goodsMapper.updateProduct(goods);
    }

    @Override
    public void addProduct(Goods goods) {
        goodsMapper.addProduct(goods);
    }

    @Override
    public void delProduct(int id) {
        goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateSale(Goods goods) {
         goodsMapper.updateSale(goods);

    }

    @Override
    public List<Goods> getSearch(String searchname,Integer typeId,Integer hasSale) {
        return goodsMapper.getSearch(searchname,typeId,hasSale);
    }

    @Override
    public List<Goods> getListByType(int id) {
        return goodsMapper.getListByType(id);
    }

    @Override
    public void uploadImg(Goods goods) {
        goodsMapper.uploadImg(goods);
    }

    @Override
    public void deleteByBatch(String[] d) {

        goodsMapper.deleteByBatch(d);
    }

    @Override
    public List<Goods> getsearchGoods(String searchname, Integer typeId, Integer maxPrice, Integer minPrice) {
        return goodsMapper.getsearchGoods(searchname,typeId,maxPrice,minPrice);
    }

    @Override
    public void updateRemrk(Goods goods) {
//        Goods goods1=goodsMapper.selectByPrimaryKey(goods.getId());
        goodsMapper.updateRemrk(goods);
    }

    @Override
    public Integer getCount(int typeId) {
        return goodsMapper.getCount(typeId);
    }


}
