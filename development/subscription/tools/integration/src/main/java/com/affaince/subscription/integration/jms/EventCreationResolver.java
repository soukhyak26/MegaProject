package com.affaince.subscription.integration.jms;

import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.EntityMetadata;
import com.affaince.subscription.common.vo.EntityType;
import com.affaince.subscription.integration.command.event.forecast.DeliveryForecastCreatedEvent;
import com.affaince.subscription.integration.command.event.forecast.ProductForecastCreatedEvent;
import com.affaince.subscription.integration.command.event.forecast.SubscriberForecastCreatedEvent;
import com.affaince.subscription.integration.command.event.forecast.SubscriptionForecastCreatedEvent;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
@Component
public class EventCreationResolver {

    public Object resolveEventForADomainEntity(EntityType entityType, Object entityId, List<DataFrameVO> dataFrameVOs, LocalDate forecastDate, EntityMetadata entityMetadata){
        switch (entityType){
            case SUBSCRIBER:
                return new SubscriberForecastCreatedEvent(dataFrameVOs,entityMetadata,forecastDate);
            case SUBSCRIPTION:
                return new SubscriptionForecastCreatedEvent(dataFrameVOs,entityMetadata,forecastDate);
            case PRODUCT:
                return  new ProductForecastCreatedEvent(entityId,dataFrameVOs,entityMetadata, forecastDate);
            case DELIVERY:
                return new DeliveryForecastCreatedEvent(dataFrameVOs,entityMetadata,forecastDate);
        }
        return null;
    }
}
