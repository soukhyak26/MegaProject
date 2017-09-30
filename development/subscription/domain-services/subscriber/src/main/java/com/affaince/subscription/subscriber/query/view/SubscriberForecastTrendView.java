package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.subscriber.vo.SubscriberTrendVersionId;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/22/2017.
 */
public class SubscriberForecastTrendView {
    private SubscriberTrendVersionId subscriberTrendVersionId;
    private LocalDate endDate;
    private long changeInTotalSubscriberCount;

    public SubscriberForecastTrendView(LocalDate trendSettingDate, LocalDate startDate, LocalDate endDate, long changeInTotalSubscriberCount) {
        this.subscriberTrendVersionId =new SubscriberTrendVersionId(trendSettingDate, startDate);
        this.endDate = endDate;
        this.changeInTotalSubscriberCount = changeInTotalSubscriberCount;
    }

    public LocalDate getTrendSettingDate() {
        return this.subscriberTrendVersionId.getTrendSettingDate();
    }


    public LocalDate getStartDate() {
        return this.subscriberTrendVersionId.getStartDate();
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
