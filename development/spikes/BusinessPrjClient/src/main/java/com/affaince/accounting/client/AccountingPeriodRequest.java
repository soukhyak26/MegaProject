package com.affaince.accounting.client;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

public class AccountingPeriodRequest implements Serializable {
    private String merchant;
    private LocalDateTime startDate;
    private LocalDateTime closureDate;
    private AccountingPeriod accountingPeriod;

    public AccountingPeriodRequest(String merchant, LocalDateTime startDate, LocalDateTime closureDate, AccountingPeriod accountingPeriod) {
        this.merchant = merchant;
        this.startDate = startDate;
        this.closureDate = closureDate;
        this.accountingPeriod = accountingPeriod;
    }
    public AccountingPeriodRequest(){}
    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(LocalDateTime closureDate) {
        this.closureDate = closureDate;
    }

    public AccountingPeriod getAccountingPeriod() {
        return accountingPeriod;
    }

    public void setAccountingPeriod(AccountingPeriod accountingPeriod) {
        this.accountingPeriod = accountingPeriod;
    }
}
