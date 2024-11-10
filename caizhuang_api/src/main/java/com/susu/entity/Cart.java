package com.susu.entity;

import java.util.List;

public class Cart {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private int quantity;

    private String size;
    //商品集合 一对多查询的时候 要在一端写入n端的集合
//    private List<Goods> goodsList;
//
//    public List<Goods> getGoodsList() {
//        return goodsList;
//    }
//
//    public void setGoodsList(List<Goods> goodsList) {
//        this.goodsList = goodsList;
//    }

    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}