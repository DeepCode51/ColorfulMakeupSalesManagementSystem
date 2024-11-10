package com.susu.entity;

public class GoodsType {
    private Integer id;

    private String goodstypename;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodstypename() {
        return goodstypename;
    }

    public void setGoodstypename(String goodstypename) {
        this.goodstypename = goodstypename == null ? null : goodstypename.trim();
    }
}