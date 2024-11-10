package com.susu.dao;

import com.susu.entity.Review;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReviewMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Review record);
    int insertSelective(Review record);
    Review selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Review record);
    int updateByPrimaryKey(Review record);
    //查看我的评价
    List<Review> showReview(@Param("userId") int userId,
                            @Param("degree") String degree);
    int deleteUserReviewById(Integer id);
    void deleteByBatch(String[] d);

    List<Review> getGoodsReview(@Param("goodsId")int goodsId,@Param("degree")String degree);

    List<Review> showBgReview(@Param("degree") String degree,
                              @Param("isReply") String isReply,
                              @Param("orderId") String orderId);

    Review toBgDetail(Review review);
}