package com.affaince.subscription.integration.command.event.forecast;


import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityMetadata;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
public class ProductForecastCreatedEvent {
    private Object Id;
    private List<DataFrameVO> dataFrameVOs;
    private EntityMetadata entityMetadata;
    private LocalDate forecastDate;

    public ProductForecastCreatedEvent(Object id, List<DataFrameVO> dataFrameVOs, EntityMetadata entityMetadata,LocalDate forecastDate) {
        Id = id;
        this.dataFrameVOs = dataFrameVOs;
        this.entityMetadata=entityMetadata;
        this.forecastDate = forecastDate;
    }

    public Object getId() {
        return Id;
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
