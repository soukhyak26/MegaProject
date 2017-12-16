package com.affaince.subscription.common.vo;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 9/3/2017.
 */
public class EntityHistoryPacket {
    private Object entityId;
    private EntityType entityType;
    //private EntityMetricType entityMetricType;
    private List<DataFrameVO> dataFrameVOs;
    private LocalDate forecastDate;
    private EntityMetadata entityMetadata;

    public EntityHistoryPacket(){}
    public EntityHistoryPacket(Object entityId, EntityType entityType, List<DataFrameVO> dataFrameVOs,LocalDate forecastDate,EntityMetadata entityMetadata) {
        this.entityId = entityId;
        this.entityType = entityType;
        //this.entityMetricType=entityMetricType;
        this.dataFrameVOs = dataFrameVOs;
        this.forecastDate=forecastDate;
        this.entityMetadata=entityMetadata;
    }

    public Object getEntityId() {
        return entityId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public List<DataFrameVO> getDataFrameVOs() {
        return dataFrameVOs;
    }

    public void setEntityId(Object entityId) {
        this.entityId = entityId;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public void setDataFrameVOs(List<DataFrameVO> dataFrameVOs) {
        this.dataFrameVOs = dataFrameVOs;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

/*
    public EntityMetricType getEntityMetricType() {
        return entityMetricType;
    }

    public void setEntityMetricType(EntityMetricType entityMetricType) {
        this.entityMetricType = entityMetricType;
    }
*/

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}
