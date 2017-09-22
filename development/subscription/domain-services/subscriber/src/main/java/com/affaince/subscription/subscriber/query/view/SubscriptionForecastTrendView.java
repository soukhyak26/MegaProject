package com.affaince.subscription.subscriber.query.view;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/22/2017.
 */
public class SubscriptionForecastTrendView {
    private LocalDate trendSettingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private long changeInTotalSubscriptionCount;

    public SubscriptionForecastTrendView(LocalDate trendSettingDate, LocalDate startDate, LocalDate endDate, long changeInTotalSubscriptionCount) {
        this.trendSettingDate = trendSettingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.changeInTotalSubscriptionCount = changeInTotalSubscriptionCount;
    }

    public LocalDate getTrendSettingDate() {
        return trendSettingDate;
    }

    public void setTrendSettingDate(LocalDate trendSettingDate) {
        this.trendSettingDate = trendSettingDate;
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

    public long getChangeInTotalSubscriptionCount() {
        return changeInTotalSubscriptionCount;
    }

    public void setChangeInTotalSubscriptionCount(long changeInTotalSubscriptionCount) {
        this.changeInTotalSubscriptionCount = changeInTotalSubscriptionCount;
    }
}
