package com.susu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class BgOrder {
    private String orderId;

    private Integer userId;

    //private Integer addId; 插入地址
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Float totalprice;

    private String deliverymethod;

    private String memo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String paymethod;

    private String status;

    private String postcode;

    private String recePhone;

    private String receName;


    private Integer totalNum;

    private Float sumPrice;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Float sumPrice) {
        this.sumPrice = sumPrice;
    }

    private List<BgOrderItem> bgOrderItemList;

    public List<BgOrderItem> getBgOrderItemList() {
        return bgOrderItemList;
    }

    public void setBgOrderItemList(List<BgOrderItem> bgOrderItemList) {
        this.bgOrderItemList = bgOrderItemList;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRecePhone() {
        return recePhone;
    }

    public void setRecePhone(String recePhone) {
        this.recePhone = recePhone;
    }

    public String getReceName() {
        return receName;
    }

    public void setReceName(String receName) {
        this.receName = receName;
    }

    //请求参数 应该单独封装的 哎 直接写在这吧,页面传递的json字符串，我要给他循环放入到订单明细中。
    private String item;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

//    public Integer getAddId() {
//        return addId;
//    }
//
//    public void setAddId(Integer addId) {
//        this.addId = addId;
//    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public String getDeliverymethod() {
        return deliverymethod;
    }

    public void setDeliverymethod(String deliverymethod) {
        this.deliverymethod = deliverymethod == null ? null : deliverymethod.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod == null ? null : paymethod.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}