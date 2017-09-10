package com.affaince.subscription.integration.jms;

import com.affaince.subscription.common.vo.EntityType;
import com.affaince.subscription.integration.command.event.forecast.ProductForecastCreatedEvent;
import com.affaince.subscription.integration.command.event.forecast.SubscriberForecastCreatedEvent;
import com.affaince.subscription.integration.command.event.forecast.SubscriptionForecastCreatedEvent;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 9/10/2017.
 */
@Component
public class EventCreationResolver {

    public Object resolveEventForADomainEntity(EntityType entityType,Object entityId,String forecastString, LocalDate forecastDate){
        switch (entityType){
            case SUBSCRIBER:
                return new SubscriberForecastCreatedEvent(forecastString,forecastDate);
            case SUBSCRIPTION:
                return new SubscriptionForecastCreatedEvent(forecastString,forecastDate);
            case PRODUCT:
                return  new ProductForecastCreatedEvent(entityId,forecastString, forecastDate);
        }
        return null;
    }
}
