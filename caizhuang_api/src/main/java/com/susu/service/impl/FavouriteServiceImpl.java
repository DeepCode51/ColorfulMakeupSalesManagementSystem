package com.susu.service.impl;

import com.susu.dao.FavouriteMapper;
import com.susu.entity.Favourite;
import com.susu.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    private FavouriteMapper favouriteMapper;

    @Override
    public void addLike(Favourite favourite) {
//        SELECT COUNT(*) FROM tb_collection WHERE user_id=2 AND goods_id=1
        Integer count=favouriteMapper.selectByUidAndPid(favourite);
        if(count ==0){
            favouriteMapper.insert(favourite);
        }else{
            favouriteMapper.delete(favourite);
        }


    }

    @Override
    public List<Favourite> showLike(int userId) {
        return favouriteMapper.showLike(userId);
    }

    @Override
    public void cancelLike(int id) {
        favouriteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void clearLike(int userId) {
        favouriteMapper.clearLike(userId);
    }

    @Override
    public void deleteByBatch(String[] d) {
        favouriteMapper.deleteByBatch(d);
    }

    @Override
    public Integer selectByUidAndPid(Favourite favourite) {
        return favouriteMapper.selectByUidAndPid(favourite);
    }

    @Override
    public List<Favourite> likeSearch(int userId,String searchname) {
        return favouriteMapper.likeSearch(userId,searchname);
    }
}
