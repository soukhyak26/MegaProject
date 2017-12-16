package com.affaince.subscription.subscriber.event;

import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityMetadata;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
public class SubscriberForecastCreatedEvent {
    private List<DataFrameVO> dataFrameVOs;
    private EntityMetadata entityMetadata;
    private LocalDate forecastDate;

    public SubscriberForecastCreatedEvent(List<DataFrameVO> dataFrameVOs,EntityMetadata entityMetadata, LocalDate forecastDate) {
        this.dataFrameVOs = dataFrameVOs;
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
}
