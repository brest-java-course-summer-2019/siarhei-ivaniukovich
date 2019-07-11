package com.epam.summer19.model;

import java.math.BigDecimal;

public class Orders {
    /**
     * Entity Id.
     */
    private Integer entityId;

    /**
     * Entity Name.
     */
    private String entityName;

    /**
     * Entity Count.
     */
    private BigDecimal entityCount;

    /**
     * Get Entity Id.
     */
    public Integer getEntityId() {
        return entityId;
    }
    /**
     * Set Entity Id.
     */
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
    /**
     * Get Entity Name.
     */
    public String getEntityName() {
        return entityName;
    }
    /**
     * Set Entity Name.
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    /**
     * Get Entity Count.
     */
    public BigDecimal getEntityCount() {
        return entityCount;
    }
    /**
     * Set Entity Count.
     */
    public void setEntityCount(BigDecimal entityCount) {
        this.entityCount = entityCount;
    }

    @Override
    public String toString() {
        return "Entity{"
                + "entityId=" + entityId
                + ", entityName='" + entityName + '\''
                + ", entityCount=" + entityCount
                + '}';
    }


}