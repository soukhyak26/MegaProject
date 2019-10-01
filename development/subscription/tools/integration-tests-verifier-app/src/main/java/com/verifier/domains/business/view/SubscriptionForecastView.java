package com.verifier.domains.business.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.verifier.domains.business.vo.SubscriptionVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 8/30/2017.
 */
@Document(collection = "SubscriptionForecastView")
public class SubscriptionForecastView {
    @Id
    private SubscriptionVersionId subscriptionVersionId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalSubscriptions;
    private ForecastContentStatus forecastContentStatus;

    public SubscriptionForecastView() {
    }

    public SubscriptionForecastView(LocalDate startDate, LocalDate endDate, LocalDate forecastDate, double valueRangeMin, double valueRangeMax) {
        this.subscriptionVersionId = new SubscriptionVersionId(startDate, forecastDate, valueRangeMin, valueRangeMax);
        this.endDate = endDate;
        this.forecastContentStatus = ForecastContentStatus.ACTIVE;
    }

    public SubscriptionForecastView(LocalDate startDate, LocalDate endDate, long newSubscriptions, long churnedSubscriptions, long totalSubscriptions, LocalDate forecastDate, double valueRangeMin, double valueRangeMax) {
        this.subscriptionVersionId = new SubscriptionVersionId(startDate, forecastDate, valueRangeMin, valueRangeMax);
        this.endDate = endDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalSubscriptions = totalSubscriptions;
        this.forecastContentStatus = ForecastContentStatus.ACTIVE;
    }

    public void addToNewSubscriptionCount(long subscriptionCount) {
        this.newSubscriptions += subscriptionCount;
    }

    public void addToChurnedSubscriptionCount(long subscriptionCount) {
        this.churnedSubscriptions += subscriptionCount;
    }

    public void addToTotalSubscriptionCount(long subscriptionCount) {
        this.totalSubscriptions += subscriptionCount;
    }


    public long getNewSubscriptions() {
        return newSubscriptions;
    }

    public long getChurnedSubscriptions() {
        return churnedSubscriptions;
    }

    public long getTotalSubscriptions() {
        return totalSubscriptions;
    }

    public void setNewSubscriptions(long newSubscriptions) {
        this.newSubscriptions = newSubscriptions;
    }

    public void setChurnedSubscriptions(long churnedSubscriptions) {
        this.churnedSubscriptions = churnedSubscriptions;
    }

    public void setTotalSubscriptions(long totalSubscriptions) {
        this.totalSubscriptions = totalSubscriptions;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public SubscriptionVersionId getSubscriptionVersionId() {
        return subscriptionVersionId;
    }
}
