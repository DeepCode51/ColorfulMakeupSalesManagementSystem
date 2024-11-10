package com.susu.entity;

public class Favourite {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private Goods goods;
//    //这个不是实体类里面有的 ，这是搜索参数 应该放在请求参数的封装中 ，避免麻烦 暂时写在这里了  算了不这么写了20203.24
//    private String searchName;
//
//    public String getSearchName() {
//        return searchName;
//    }
//
//    public void setSearchName(String searchName) {
//        this.searchName = searchName;
//    }

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
}