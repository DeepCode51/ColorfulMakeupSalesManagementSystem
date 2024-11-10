package com.susu.entity;

import java.math.BigDecimal;

public class OrderItem {
    private Integer itemId;

    private String orderId;

    private Integer orderGoodsId;
    //数量
    private Integer quantity;
    //购买时候的价格
    private BigDecimal goodsBuyPrice;

    private String size;

    //明细状态
    private String status;

    public String getStatus() {
        return status;
    }

    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", orderId='" + orderId + '\'' +
                ", orderGoodsId=" + orderGoodsId +
                ", quantity=" + quantity +
                ", goodsBuyPrice=" + goodsBuyPrice +
                ", size='" + size + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getGoodsBuyPrice() {
        return goodsBuyPrice;
    }

    public void setGoodsBuyPrice(BigDecimal goodsBuyPrice) {
        this.goodsBuyPrice = goodsBuyPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Integer orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }
}