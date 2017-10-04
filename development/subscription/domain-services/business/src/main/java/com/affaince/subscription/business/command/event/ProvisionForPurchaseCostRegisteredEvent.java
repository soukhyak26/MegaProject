package com.affaince.subscription.business.command.event;

import com.affaince.subscription.business.vo.InstallmentPerCalendarPeriod;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForPurchaseCostRegisteredEvent {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double provisionForPurchaseOfGoods;
    private List<InstallmentPerCalendarPeriod> calendar;
    public ProvisionForPurchaseCostRegisteredEvent(Integer Id,LocalDate startDate, LocalDate endDate, double provisionForPurchaseOfGoods,List<InstallmentPerCalendarPeriod> calendar) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.provisionForPurchaseOfGoods=provisionForPurchaseOfGoods;
        this.calendar=calendar;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForPurchaseOfGoods() {
        return provisionForPurchaseOfGoods;
    }

    public List<InstallmentPerCalendarPeriod> getCalendar() {
        return calendar;
    }
}
