package com.affaince.accounting.pnl;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.trading.TradingAccountEntry;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class ProfitAndLossAccount {
    private final String merchantId;
    private final String accountId;
    protected LocalDateTime startDate;
    protected LocalDateTime closureDate;
    private final AccountIdentifier accountIdentifier;
    private List<ProfitAndLossAccountEntry> debits;
    private List<ProfitAndLossAccountEntry> credits;
    private boolean isLatestVersion;

    public ProfitAndLossAccount(String merchantId, String accountId,AccountIdentifier accountIdentifier,LocalDateTime startDate, LocalDateTime closureDate) {
        this.merchantId = merchantId;
        this.accountId = accountId;
        this.startDate=startDate;
        this.accountIdentifier = accountIdentifier;
        this.debits = new ArrayList<>();
        this.credits = new ArrayList<>();
        this.closureDate = closureDate;
        this.isLatestVersion=true;
    }

    public void debit(ProfitAndLossAccountEntry debitEntry) {
        if (null != debitEntry) {
            this.debits.add(debitEntry);
        }
    }

    public void credit(ProfitAndLossAccountEntry creditEntry) {
        if (null != creditEntry) {
            this.credits.add(creditEntry);
        }
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getAccountId() {
        return accountId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getClosureDate() {
        return closureDate;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public List<ProfitAndLossAccountEntry> getDebits() {
        return debits;
    }

    public List<ProfitAndLossAccountEntry> getCredits() {
        return credits;
    }

    public boolean getLatestVersion() {
        return isLatestVersion;
    }

    @Override
    public String toString() {
        return "ProfitAndLossAccount{" +
                "merchantId='" + merchantId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", startDate=" + startDate +
                ", closureDate=" + closureDate +
                ", accountIdentifier=" + accountIdentifier +
                ", debits=" + debits +
                ", credits=" + credits +
                ", isLatestVersion=" + isLatestVersion +
                '}';
    }

}
