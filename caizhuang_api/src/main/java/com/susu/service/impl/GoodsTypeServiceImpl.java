package com.susu.service.impl;


import com.susu.dao.GoodsMapper;
import com.susu.dao.GoodsTypeMapper;
import com.susu.entity.GoodsType;
import com.susu.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsType> getGoodsType() {

            return goodsTypeMapper.getGoodsType();
    }

    @Override
    public void addGoodsType(GoodsType goodsType) {
         goodsTypeMapper.insert(goodsType);
    }

    @Override
    public void editGoodsType(GoodsType goodsType) {
        goodsTypeMapper.updateByPrimaryKey(goodsType);
    }

    @Override
    public void delType(int id) {
        goodsTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GoodsType getTypeById(int id) {
        return goodsTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean count(String goodstypename) {
        int count = goodsTypeMapper.count(goodstypename);
        return count == 0;
//        return goodsTypeMapper.count(goodstypename);
    }

    @Override
    public void deleteByBatch(String[] ids) {
        goodsTypeMapper.deleteByBatch(ids);
    }


}
