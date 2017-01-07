package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 11-10-2016.
 */
public interface ProductSubscriptionMetricsView {

    ProductVersionId getProductVersionId();

    LocalDate getEndDate();

    void setEndDate(LocalDate endDate);

    long getNewSubscriptions();

    void setNewSubscriptions(long newSubscriptions);

    long getChurnedSubscriptions();

    void setChurnedSubscriptions(long churnedSubscriptions);

    long getTotalNumberOfExistingSubscriptions();

    void setTotalNumberOfExistingSubscriptions(long totalNumberOfExistingSubscriptions);
}
