package com.affaince.subscription.product.registration.query.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandark on 28-01-2016.
 */
@Document
public class ForecastedPriceBucketsView {
    @Id
    private String productId;
    private double purchasePricePerUnit;
    private double offeredPricePerUnit;
    private double MRP;
    private LocalDate fromDate;
    private LocalDate toDate;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private long numberOfExistingCustomersAssociatedWithAPrice;

}
