package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.util.VersionNumberGenerator;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractLedgerAccountStereoType implements LedgerAccount {
    private final String merchantId;
    private final String accountId;
    private LocalDateTime startDate;
    private LocalDateTime closureDate;
    private final AccountIdentifier accountIdentifier;
    private Set<DebitLedgerEntry> debits;
    private Set<CreditLedgerEntry> credits;
    private boolean isLatestVersion;



    public AbstractLedgerAccountStereoType(String merchantId, String accountId,AccountIdentifier accountIdentifier) {
        this.merchantId = merchantId;
        this.accountId = accountId;
        this.startDate=LocalDateTime.now();
        this.accountIdentifier = accountIdentifier;
        this.debits = new TreeSet<>(Comparator.comparing(LedgerAccountEntry::getDate));
        this.credits = new TreeSet<>(Comparator.comparing(LedgerAccountEntry::getDate));
        this.closureDate = new LocalDateTime(9999, 12, 31,00,00,0000);
        this.isLatestVersion=true;
    }

    @Override
    public Object clone() {
        try {
            LedgerAccount cloned = (LedgerAccount) super.clone();
            //Set<DebitLedgerEntry> debitsClone = debits.stream().map(debitEntry -> (DebitLedgerEntry) debitEntry.clone()).collect(Collectors.toSet());
            //Set<CreditLedgerEntry> creditsClone = credits.stream().map(creditEntry -> (CreditLedgerEntry) creditEntry.clone()).collect(Collectors.toSet());
            this.startDate=LocalDateTime.now();
            this.closureDate = new LocalDateTime(9999, 12, 31,00,00,0000);
            ((AbstractLedgerAccountStereoType) cloned).setClone(new HashSet<>(), new HashSet<>());
            return cloned;
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public void closeActiveVersion(LocalDateTime closureDate) {
        this.closureDate = closureDate;
        this.isLatestVersion=true;
    }

    public LocalDateTime getStartDate() {
        return startDate;
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

    public Set<DebitLedgerEntry> getDebits() {
        return debits;
    }

    public Set<CreditLedgerEntry> getCredits() {
        return credits;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }
    @Override
    public void flushAllEntries(){
        this.debits.removeAll(this.debits);
        this.credits.removeAll(this.credits);
    }
    public void debit(DebitLedgerEntry debitEntry) {
        if (null != debitEntry) {
            this.debits.add(debitEntry);
        }
    }

    public void credit(CreditLedgerEntry creditEntry) {
        if (null != creditEntry) {
            this.credits.add(creditEntry);
        }
    }

    public void setClone(Set<DebitLedgerEntry> debitsCloned, Set<CreditLedgerEntry> creditsCloned) {
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
