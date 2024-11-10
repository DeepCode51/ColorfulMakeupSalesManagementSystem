package com.susu.service.impl;

import com.susu.dao.BgReviewMapper;
import com.susu.dao.ReviewMapper;
import com.susu.entity.BgReview;
import com.susu.entity.Review;
import com.susu.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private BgReviewMapper bgReviewMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bgReviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteUserReviewById(Integer id) {
        return reviewMapper.deleteUserReviewById(id);
    }

    @Override
    public void insertReview(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public List<Review> showReview(int userId,String degree) {
        return reviewMapper.showReview(userId,degree);
    }

    @Override
    public void updateReview(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public void deleteByBatch(String[] d) {
        reviewMapper.deleteByBatch(d);
    }

    @Override
    public List<Review> getGoodsReview(int goodsId,String degree) {
        return reviewMapper.getGoodsReview(goodsId,degree);
    }

    @Override
    public List<Review> showBgReview(String degree, String isReply,String orderId) {
        return reviewMapper.showBgReview(degree,isReply,orderId);
    }

    @Override
    public Review toBgDetail(Review review) {
        return reviewMapper.toBgDetail(review);
    }

    @Override
    public void insertBgReview(BgReview bgReview) {
        bgReviewMapper.insert(bgReview);
    }

    @Override
    public List<BgReview> reviewByBg(Integer userId,String degree) {
        return bgReviewMapper.reviewByBg(userId,degree);
    }
}

