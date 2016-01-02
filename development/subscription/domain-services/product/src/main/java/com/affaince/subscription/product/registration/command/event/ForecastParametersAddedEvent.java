package com.affaince.subscription.product.registration.command.event;

import com.affaince.subscription.product.registration.command.domain.PriceBucket;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class ForecastParametersAddedEvent {
    private String productId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private PriceBucket priceBucket;

    public ForecastParametersAddedEvent(String productId, LocalDate fromDate, LocalDate toDate, PriceBucket priceBucket) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.priceBucket = priceBucket;
    }

    public ForecastParametersAddedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public PriceBucket getPriceBuckets() {
        return priceBucket;
    }
}
