package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 10-07-2016.
 */
@Document(collection = "ProductForecastView")
public class ProductForecastView implements ProductSubscriptionMetricsView {
    @Id
    private final ForecastVersionId forecastVersionId;
    private LocalDate endDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalNumberOfExistingSubscriptions;
    private ForecastContentStatus forecastContentStatus;

    public ProductForecastView(ForecastVersionId forecastVersionId, LocalDate endDate){
        this.forecastVersionId = forecastVersionId;
        this.endDate = endDate;

    }
    public ProductForecastView(ForecastVersionId forecastVersionId, LocalDate endDate, long newSubscriptions, long churnedSubscriptions, long totalNumberOfExistingSubscriptions) {
        this.forecastVersionId = forecastVersionId;
        this.endDate = endDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
        this.forecastContentStatus = ForecastContentStatus.ACTIVE;
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
    public LocalDate getForecastDate() {
        return this.forecastVersionId.getForecastDate();
    }
    public ProductVersionId getProductVersionId(){return this.forecastVersionId;}
    public ForecastVersionId getForecastVersionId(){return this.forecastVersionId;}

}
