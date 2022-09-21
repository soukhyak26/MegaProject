package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

import java.util.*;

public abstract class LedgerAccount /*implements Cloneable*/ {
    private final String merchantId;
    private final String accountId;
    protected LocalDateTime startDate;
    protected LocalDateTime closureDate;
    private final AccountIdentifier accountIdentifier;
    private List<LedgerAccountEntry> debits;
    private List<LedgerAccountEntry> credits;
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

/*
    @Override
    public Object clone() {
        try {
            LedgerAccount cloned = (LedgerAccount) super.clone();
            //Set<DebitLedgerEntry> debitsClone = debits.stream().map(debitEntry -> (DebitLedgerEntry) debitEntry.clone()).collect(Collectors.toSet());
            //Set<CreditLedgerEntry> creditsClone = credits.stream().map(creditEntry -> (CreditLedgerEntry) creditEntry.clone()).collect(Collectors.toSet());
*/
/*
            this.startDate=LocalDateTime.now();
            this.closureDate = new LocalDateTime(9999, 12, 31,00,00,0000);
*//*

            cloned.setClone(new HashSet<>(), new HashSet<>());
            return cloned;
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return null;
    }
*/
/*
    public void closeActiveVersion(LocalDateTime closureDate) {
        //this.closureDate = closureDate;
        this.isLatestVersion=true;
    }
*/

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
    public void flushAllEntries(){
        this.debits.removeAll(this.debits);
        this.credits.removeAll(this.credits);
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

    public void setClone(List<LedgerAccountEntry> debitsCloned, List<LedgerAccountEntry> creditsCloned) {
        this.debits = debitsCloned;
        this.credits = creditsCloned;
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
