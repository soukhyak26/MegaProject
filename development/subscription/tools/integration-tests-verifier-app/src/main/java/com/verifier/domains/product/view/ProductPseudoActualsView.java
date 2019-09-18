package com.verifier.domains.product.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 10-07-2016.
 */

@Document(collection = "ProductPseudoActualsView")
public class ProductPseudoActualsView implements ProductSubscriptionMetricsView{
    @Id
    private ForecastVersionId forecastVersionId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalNumberOfExistingSubscriptions;
    private ForecastContentStatus forecastContentStatus;

    public ProductPseudoActualsView(ForecastVersionId forecastVersionId, LocalDate endDate){
        this.forecastVersionId = forecastVersionId;
        this.endDate = endDate;
    }
    public ProductPseudoActualsView(ForecastVersionId forecastVersionId, LocalDate endDate, long newSubscriptions, long churnedSubscriptions, long totalNumberOfExistingSubscriptions) {
        this.forecastVersionId = forecastVersionId;
        this.endDate = endDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
        this.forecastContentStatus = ForecastContentStatus.ACTIVE;
    }

    public ProductPseudoActualsView() {
    }

    @Override
    @JsonIgnore
    public ProductVersionId getProductVersionId() {
        return this.forecastVersionId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getNewSubscriptions() {
        return newSubscriptions;
    }

    public void setNewSubscriptions(long newSubscriptions) {
        this.newSubscriptions = newSubscriptions;
    }

    public long getChurnedSubscriptions() {
        return churnedSubscriptions;
    }

    public void setChurnedSubscriptions(long churnedSubscriptions) {
        this.churnedSubscriptions = churnedSubscriptions;
    }

    public long getTotalNumberOfExistingSubscriptions() {
        return totalNumberOfExistingSubscriptions;
    }

    public void setTotalNumberOfExistingSubscriptions(long totalNumberOfExistingSubscriptions) {
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
    @JsonIgnore
    public LocalDate getForecastDate() {
        return forecastVersionId.getForecastDate();
    }

    public ForecastVersionId getForecastVersionId() {
        return forecastVersionId;
    }

    public void setForecastVersionId(ForecastVersionId forecastVersionId) {
        this.forecastVersionId = forecastVersionId;
    }
}
