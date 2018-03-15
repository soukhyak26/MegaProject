package com.verifier.domains.product.view;

import com.affaince.subscription.common.deserializer.LocalDateTimeDeserializer;
import com.affaince.subscription.common.serializer.LocalDateTimeSerializer;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 21-10-2016.
 * Initially merchant is expected to set target manually in this view.
 * Later forecast from earlier year will continue as target for next year
 * Unlike ProductForecastView where forecast keeps changing as per product's performance
 * Target values will be set at the start of the year and will not change
 * It is used to measure actual performance of a product with set targets
 */
@Document(collection = "TargetSettingView")
public class TargetSettingView {
    @Id
    private final ProductVersionId productVersionId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate endDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalNumberOfExistingSubscriptions;
    private double fixedExpensesPerPeriod;
    private double variableExpensesPerPeriod;


    public TargetSettingView(ProductVersionId productVersionId, LocalDate endDate, long newSubscriptions, long churnedSubscriptions, long totalNumberOfExistingSubscriptions, double fixedExpensesPerPeriod, double variableExpensesPerPeriod) {
        this.productVersionId = productVersionId;
        this.endDate = endDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
        this.fixedExpensesPerPeriod = fixedExpensesPerPeriod;
        this.variableExpensesPerPeriod = variableExpensesPerPeriod;
    }

    public ProductVersionId getProductVersionId() {
        return productVersionId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getNewSubscriptions() {
        return newSubscriptions;
    }

    public long getChurnedSubscriptions() {
        return churnedSubscriptions;
    }

    public long getTotalNumberOfExistingSubscriptions() {
        return totalNumberOfExistingSubscriptions;
    }

    public double getFixedExpensesPerPeriod() {
        return fixedExpensesPerPeriod;
    }

    public double getVariableExpensesPerPeriod() {
        return variableExpensesPerPeriod;
    }
}
