package com.affaince.subscription.product.command;

import com.affaince.subscription.common.vo.EntityMetricType;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 29-04-2016.
 */
public class UpdateForecastFromActualsCommand {
    @TargetAggregateIdentifier
    private final Integer productAnalyserId;
    private final String productId;
    private final EntityMetricType entityMetricType;
    private final LocalDate forecastDate;

    public UpdateForecastFromActualsCommand(Integer productAnalyserId,String productId,EntityMetricType entityMetricType, LocalDate forecastDate) {
        this.productAnalyserId=productAnalyserId;
        this.productId = productId;
        this.entityMetricType=entityMetricType;
        this.forecastDate = forecastDate;
    }

    public Integer getProductAnalyserId() {
        return productAnalyserId;
    }

    public EntityMetricType getEntityMetricType() {
        return entityMetricType;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

}
