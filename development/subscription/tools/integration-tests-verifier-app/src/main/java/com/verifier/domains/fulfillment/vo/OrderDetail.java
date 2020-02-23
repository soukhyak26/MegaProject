package com.verifier.domains.fulfillment.vo;


import org.joda.time.LocalDate;

public class OrderDetail {
    private LocalDate startDate;
    private LocalDate endDate;
    private long count;

    public OrderDetail(LocalDate startDate, LocalDate endDate, long count) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.count = count;
    }

    public OrderDetail() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getCount() {
        return count;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
