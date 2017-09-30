package com.affaince.subscription.subscriber.vo;

import org.joda.time.LocalDate;
import scala.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class SubscriberTrendVersionId implements Serializable {
    private final LocalDate trendSettingDate;
    private final LocalDate startDate;

    public SubscriberTrendVersionId(LocalDate trendSettingDate, LocalDate startDate) {
        this.trendSettingDate = trendSettingDate;
        this.startDate = startDate;
    }

    public LocalDate getTrendSettingDate() {
        return trendSettingDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
