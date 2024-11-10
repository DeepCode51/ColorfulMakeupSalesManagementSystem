package com.susu.entity;

import java.math.BigDecimal;

public class BgOrderItem {
    private Integer itemId;

    private String orderId;

    private Integer orderGoodsId;

    private Integer quantity;

    private BigDecimal goodsbuyprice;

    private String size;

    private String status;

    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "BgOrderItem{" +
                "itemId=" + itemId +
                ", orderId='" + orderId + '\'' +
                ", orderGoodsId=" + orderGoodsId +
                ", quantity=" + quantity +
                ", goodsbuyprice=" + goodsbuyprice +
                ", size='" + size + '\'' +
                ", status='" + status + '\'' +
                '}';
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getGoodsbuyprice() {
        return goodsbuyprice;
    }

    public void setGoodsbuyprice(BigDecimal goodsbuyprice) {
        this.goodsbuyprice = goodsbuyprice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}