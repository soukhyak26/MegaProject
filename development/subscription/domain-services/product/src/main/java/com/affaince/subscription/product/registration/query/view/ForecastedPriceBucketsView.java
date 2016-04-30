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
    private LocalDate fromDate;
    private LocalDate toDate;
    private double purchasePricePerUnit;
    private double MRP;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;

    private double offeredPricePerUnit;
    private long numberOfExistingCustomersAssociatedWithAPrice;

    public ForecastedPriceBucketsView(String productId, LocalDate fromDate, LocalDate toDate, double purchasePricePerUnit, double MRP, long numberOfNewCustomersAssociatedWithAPrice, long numberOfChurnedCustomersAssociatedWithAPrice) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
    }
}
