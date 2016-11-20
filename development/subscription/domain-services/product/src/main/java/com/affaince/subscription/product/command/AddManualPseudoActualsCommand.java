package com.affaince.subscription.product.command;

import com.affaince.subscription.product.vo.ProductForecastParameter;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 07-10-2016.
 */
public class AddManualPseudoActualsCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final ProductForecastParameter[] forecastParameters;
    private long totalNumberOfSubscriptions;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public AddManualPseudoActualsCommand(String productId, ProductForecastParameter[] forecastParameters, long totalNumberOfSubscriptions, LocalDateTime startDate, LocalDateTime endDate) {
        this.productId = productId;
        this.forecastParameters=forecastParameters;
        this.totalNumberOfSubscriptions=totalNumberOfSubscriptions;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public ProductForecastParameter[] getForecastParameters() {
        return forecastParameters;
    }

    public long getTotalNumberOfSubscriptions() {
        return totalNumberOfSubscriptions;
    }
}
