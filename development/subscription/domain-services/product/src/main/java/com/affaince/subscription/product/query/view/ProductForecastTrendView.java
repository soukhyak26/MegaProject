package com.affaince.subscription.product.query.view;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/21/2017.
 */
public class ProductForecastTrendView {
    private String productId;
    private LocalDate trendSettingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private long changeInTotalSusbcriptionCount;

    public ProductForecastTrendView(String productId, LocalDate trendSettingDate, LocalDate startDate, LocalDate endDate, long changeInTotalSusbcriptionCount) {
        this.productId = productId;
        this.trendSettingDate = trendSettingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.changeInTotalSusbcriptionCount = changeInTotalSusbcriptionCount;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getTrendSettingDate() {
        return trendSettingDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getChangeInTotalSusbcriptionCount() {
        return changeInTotalSusbcriptionCount;
    }
}
