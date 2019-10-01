package com.verifier.domains.product.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.verifier.domains.product.vo.ProductVersionId;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 11-10-2016.
 */
public interface ProductSubscriptionMetricsView {

    ProductVersionId getProductVersionId();
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate getEndDate();

    void setEndDate(LocalDate endDate);

    long getNewSubscriptions();

    void setNewSubscriptions(long newSubscriptions);

    long getChurnedSubscriptions();

    void setChurnedSubscriptions(long churnedSubscriptions);

    long getTotalNumberOfExistingSubscriptions();

    void setTotalNumberOfExistingSubscriptions(long totalNumberOfExistingSubscriptions);
    public LocalDate getForecastDate();
}
