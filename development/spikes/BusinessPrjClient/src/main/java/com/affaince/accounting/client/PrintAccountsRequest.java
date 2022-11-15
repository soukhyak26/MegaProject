package com.affaince.accounting.client;

import org.joda.time.LocalDateTime;

public class PrintAccountsRequest {
    private String merchantId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public PrintAccountsRequest(String merchantId, LocalDateTime startDate, LocalDateTime endDate) {
        this.merchantId = merchantId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PrintAccountsRequest() {
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
