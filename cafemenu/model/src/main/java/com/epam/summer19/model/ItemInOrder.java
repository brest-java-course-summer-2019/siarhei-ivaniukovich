package com.epam.summer19.model;

import java.math.BigDecimal;

public class ItemInOrder {

    /**
     * Item In Order - Order ID
     */
    private Integer iioOrderId;

    /**
     * Item In Order - Item ID (from Item)
     */
    private Integer iioItemId;

    /**
     * Item In Order - Item name (from Item)
     */
    private String iioItemName;

    /**
     * Item In Order - Item price (from Item)
     */
    private BigDecimal iioItemPrice;

    /**
     * Item In Order - Item count
     */
    private Integer iioItemCount;

    public Integer getIioOrderId() {
        return iioOrderId;
    }

    public void setIioOrderId(Integer iioOrderId) {
        this.iioOrderId = iioOrderId;
    }

    public Integer getIioItemId() {
        return iioItemId;
    }

    public void setIioItemId(Integer iioItemId) {
        this.iioItemId = iioItemId;
    }

    public String getIioItemName() {
        return iioItemName;
    }

    public void setIioItemName(String iioItemName) {
        this.iioItemName = iioItemName;
    }

    public BigDecimal getIioItemPrice() {
        return iioItemPrice;
    }

    public void setIioItemPrice(BigDecimal iioItemPrice) {
        this.iioItemPrice = iioItemPrice;
    }

    public Integer getIioItemCount() {
        return iioItemCount;
    }

    public void setIioItemCount(Integer iioItemCount) {
        this.iioItemCount = iioItemCount;
    }
}
