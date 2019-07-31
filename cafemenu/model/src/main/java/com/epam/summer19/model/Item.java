package com.epam.summer19.model;

import java.math.BigDecimal;

public class Item {
    /**
     * Item ID
     */
    private Integer itemId;

    /**
     * Item name
     */
    private String itemName;

    /**
     * Item price
     */
    private BigDecimal itemPrice;

    /**
     * Get Item ID
     * @return
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * Set Item ID
     * @param itemId
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * Get Item Name
     * @return
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Set Item Name
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Get Item Price
     * @return
     */
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    /**
     * Set Item price
     * @param itemPrice
     */
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
}