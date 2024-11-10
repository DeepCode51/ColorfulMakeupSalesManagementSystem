package com.susu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;

    private Integer userId;

    private Integer addId;

    private Float totalprice;

    private String deliverymethod;

    private String memo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String paymethod;

    private String status;

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
//    private OrderItem orderItem;
//
//    public OrderItem getOrderItem() {
//        return orderItem;
//    }
//
//    public void setOrderItem(OrderItem orderItem) {
//        this.orderItem = orderItem;
//    }
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", addId=" + addId +
                ", totalprice=" + totalprice +
                ", deliverymethod='" + deliverymethod + '\'' +
                ", memo='" + memo + '\'' +
                ", createTime=" + createTime +
                ", paymethod='" + paymethod + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }
    //收货状态
    public void setStatus(String status) {
        this.status = status;
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

    public Integer getAddId() {
        return addId;
    }

    public void setAddId(Integer addId) {
        this.addId = addId;
    }

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


}