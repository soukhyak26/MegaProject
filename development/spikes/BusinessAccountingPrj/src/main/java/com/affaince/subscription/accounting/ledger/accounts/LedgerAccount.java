package com.affaince.subscription.accounting.ledger.accounts;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

import java.util.*;

public abstract class LedgerAccount {
    private final String merchantId;
    private final String accountId;
    protected LocalDateTime startDate;
    protected LocalDateTime closureDate;
    private final AccountIdentifier accountIdentifier;
    private final List<LedgerAccountEntry> debits;
    private final List<LedgerAccountEntry> credits;
    private boolean isLatestVersion;



    public LedgerAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        this.merchantId = merchantId;
        this.accountId = accountId;
        this.startDate=startDate;
        this.accountIdentifier = accountIdentifier;
        this.debits = new ArrayList<>();
        this.credits = new ArrayList<>();
        this.closureDate = closureDate;
        this.isLatestVersion=true;
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

    public String getMerchantId() {
        return merchantId;
    }

    public String getAccountId() {
        return accountId;
    }

    public List<LedgerAccountEntry> getDebits() {
        return debits;
    }

    public List<LedgerAccountEntry> getCredits() {
        return credits;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }
    public void debit(LedgerAccountEntry debitEntry) {
        if (null != debitEntry) {
            this.debits.add(debitEntry);
        }
    }

    public void credit(LedgerAccountEntry creditEntry) {
        if (null != creditEntry) {
            this.credits.add(creditEntry);
        }
    }

    public boolean getLatestVersion(){
        return isLatestVersion;
    }
    public void setLatestVersion(boolean isLatestVersion){
        this.isLatestVersion=isLatestVersion;
    }
    @Override
    public String toString() {
        return "{" +
                "merchantId='" + merchantId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", debits=" + debits +
                ", credits=" + credits +
                ", closureDate=" + closureDate +
                '}';
    }
}
