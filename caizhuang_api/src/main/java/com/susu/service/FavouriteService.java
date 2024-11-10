package com.susu.service;

import com.susu.entity.Favourite;

import java.util.List;

public interface FavouriteService {
    void addLike(Favourite favourite);

    List<Favourite> showLike(int userId);

    void cancelLike(int id);

    void clearLike(int userId);

    void deleteByBatch(String[] d);

    Integer selectByUidAndPid(Favourite favourite);

    List<Favourite> likeSearch(int userId,String searchname);
}
