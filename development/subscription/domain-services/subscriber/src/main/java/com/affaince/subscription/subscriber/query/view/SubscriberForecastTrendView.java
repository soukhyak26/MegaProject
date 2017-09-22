package com.affaince.subscription.subscriber.query.view;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/22/2017.
 */
public class SubscriberForecastTrendView {
    private LocalDate trendSettingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private long changeInTotalSubscriberCount;

    public SubscriberForecastTrendView(LocalDate trendSettingDate, LocalDate startDate, LocalDate endDate, long changeInTotalSubscriberCount) {
        this.trendSettingDate = trendSettingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.changeInTotalSubscriberCount = changeInTotalSubscriberCount;
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

    public long getChangeInTotalSubscriberCount() {
        return changeInTotalSubscriberCount;
    }

    public void setChangeInTotalSubscriberCount(long changeInTotalSubscriberCount) {
        this.changeInTotalSubscriberCount = changeInTotalSubscriberCount;
    }

}
