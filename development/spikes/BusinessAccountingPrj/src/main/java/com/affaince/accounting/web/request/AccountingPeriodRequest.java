package com.affaince.accounting.web.request;

import com.affaince.accounting.reconcile.AccountingPeriod;
import org.joda.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AccountingPeriodRequest implements Serializable {
    @NotNull
    private String merchant;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime closureDate;
    @NotNull
    private AccountingPeriod accountingPeriod;

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
