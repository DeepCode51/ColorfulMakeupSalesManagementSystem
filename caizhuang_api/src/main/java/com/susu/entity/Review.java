package com.susu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Review {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String goodsSize;

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private Integer orderitemId;

    private String degree;

    private String content;

    private String goodscore;

    private String shopscore;

    private String logisticscore;

    private String img;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String replyContent;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date replyTime;

    private Goods goods;

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", goodsSize='" + goodsSize + '\'' +
                ", orderitemId=" + orderitemId +
                ", degree='" + degree + '\'' +
                ", content='" + content + '\'' +
                ", goodscore='" + goodscore + '\'' +
                ", shopscore='" + shopscore + '\'' +
                ", logisticscore='" + logisticscore + '\'' +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime=" + replyTime +
                ", goods=" + goods +
                '}';
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize == null ? null : goodsSize.trim();
    }

    public Integer getOrderitemId() {
        return orderitemId;
    }

    public void setOrderitemId(Integer orderitemId) {
        this.orderitemId = orderitemId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getGoodscore() {
        return goodscore;
    }

    public void setGoodscore(String goodscore) {
        this.goodscore = goodscore == null ? null : goodscore.trim();
    }

    public String getShopscore() {
        return shopscore;
    }

    public void setShopscore(String shopscore) {
        this.shopscore = shopscore == null ? null : shopscore.trim();
    }

    public String getLogisticscore() {
        return logisticscore;
    }

    public void setLogisticscore(String logisticscore) {
        this.logisticscore = logisticscore == null ? null : logisticscore.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
}