package com.susu.dao;

import com.susu.entity.BgReview;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BgReviewMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(BgReview record);
    int insertSelective(BgReview record);
    BgReview selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(BgReview record);
    int updateByPrimaryKey(BgReview record);

    List<BgReview> reviewByBg(@Param("userId") Integer userId,
                              @Param("degree") String degree);
}