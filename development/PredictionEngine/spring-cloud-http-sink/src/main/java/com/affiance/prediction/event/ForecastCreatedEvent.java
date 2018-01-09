package com.affiance.prediction.event;

import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityType;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 9/23/2017.
 */
public class ForecastCreatedEvent {
    private EntityType entityType;
    private Object entityId;
    private List<DataFrameVO> dataFrameVOs;
    private EntityMetadata entityMetadata;
    private LocalDate forecastDate;

    public ForecastCreatedEvent(){}
    public ForecastCreatedEvent(EntityType entityType, Object entityId, List<DataFrameVO> dataFrameVOs, EntityMetadata entityMetadata, LocalDate forecastDate) {
        this.entityType=entityType;
        this.entityId=entityId;
        this.dataFrameVOs=dataFrameVOs;
        this.entityMetadata=entityMetadata;
        this.forecastDate = forecastDate;
    }

    public List<DataFrameVO> getDataFrameVOs() {
        return dataFrameVOs;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public Object getEntityId() {
        return entityId;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
