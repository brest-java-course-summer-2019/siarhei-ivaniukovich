package com.epam.summer19;

import java.math.BigDecimal;


public class Orders {

    private Integer entityId;

    private String entityName;

    private BigDecimal entityCount;

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public BigDecimal getEntityCount() {
        return entityCount;
    }

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