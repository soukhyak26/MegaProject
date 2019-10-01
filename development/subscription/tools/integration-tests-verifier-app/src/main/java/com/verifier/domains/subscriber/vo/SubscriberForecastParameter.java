package com.verifier.domains.subscriber.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.affaince.subscription.common.type.ForecastContentStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 10/28/2017.
 */
public class SubscriberForecastParameter {
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private long numberOfNewSubscribers;
    private long numberOfChurnedSubscribers;
    //  private long numberOfTotalSubscriptions;
    private ForecastContentStatus forecastContentStatus;

    public SubscriberForecastParameter(LocalDate startDate, LocalDate endDate, long numberOfNewSubscribers, long numberOfChurnedSubscribers, ForecastContentStatus forecastContentStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfNewSubscribers = numberOfNewSubscribers;
        this.numberOfChurnedSubscribers = numberOfChurnedSubscribers;
        this.forecastContentStatus = forecastContentStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getNumberOfNewSubscribers() {
        return numberOfNewSubscribers;
    }

    public void setNumberOfNewSubscribers(long numberOfNewSubscribers) {
        this.numberOfNewSubscribers = numberOfNewSubscribers;
    }

    public long getNumberOfChurnedSubscribers() {
        return numberOfChurnedSubscribers;
    }

    public void setNumberOfChurnedSubscribers(long numberOfChurnedSubscribers) {
        this.numberOfChurnedSubscribers = numberOfChurnedSubscribers;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
}
