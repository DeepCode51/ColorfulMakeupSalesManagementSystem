package com.susu.service;

import com.susu.entity.BgReview;
import com.susu.entity.Review;

import java.util.List;

public interface ReviewService {
    int deleteByPrimaryKey(Integer id);

    int deleteUserReviewById(Integer id);

    void insertReview(Review review);

    List<Review> showReview(int userId,String review);

    void updateReview(Review review);

    void deleteByBatch(String[] d);

    List<Review> getGoodsReview(int goodsId,String degree);

    List<Review> showBgReview(String degree, String isReply,String orderId);

    Review toBgDetail(Review review);

    void insertBgReview(BgReview bgReview);

    List<BgReview> reviewByBg(Integer userId,String degree);
}
