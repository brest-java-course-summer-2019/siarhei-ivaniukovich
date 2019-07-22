package com.epam.summer19.model;

import java.math.BigDecimal;

public class Orders {
    /**
     * Entity Id.
     */
    private Integer itemId;

    /**
     * Entity Name.
     */
    private String itemName;

    /**
     * Entity Count.
     */
    private BigDecimal itemCount;

    /**
     * Get Entity Id.
     */
    public Integer getEntityId() {
        return itemId;
    }
    /**
     * Set Entity Id.
     */
    public void setEntityId(Integer entityId) {
        this.itemId = entityId;
    }
    /**
     * Get Entity Name.
     */
    public String getEntityName() {
        return itemName;
    }
    /**
     * Set Entity Name.
     */
    public void setEntityName(String itemName) {
        this.itemName = itemName;
    }
    /**
     * Get Entity Count.
     */
    public BigDecimal getEntityCount() {
        return itemCount;
    }
    /**
     * Set Entity Count.
     */
    public void setEntityCount(BigDecimal entityCount) {
        this.itemCount = entityCount;
    }

    @Override
    public String toString() {
        return "Entity{"
                + "entityId=" + itemId
                + ", entityName='" + itemName + '\''
                + ", entityCount=" + itemCount
                + '}';
    }


}