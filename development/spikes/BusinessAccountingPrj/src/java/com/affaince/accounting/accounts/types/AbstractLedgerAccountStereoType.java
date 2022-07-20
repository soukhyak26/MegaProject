package com.affaince.accounting.accounts.types;

import com.affaince.accounting.journal.qualifiers.AccountQualifiers;

import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractLedgerAccountStereoType {
    private String accountId;
    Set<DebitEntry> debits;
    Set<CreditEntry> credits;

    public AbstractLedgerAccountStereoType(String accountId) {
        this.accountId= accountId;
        AccountEntryComparator comparator = new AccountEntryComparator();
        this.debits = new TreeSet<>(comparator);
        this.credits = new TreeSet<>(comparator);
    }

    public String getAccountId() {
        return accountId;
    }


    public Set<DebitEntry> getDebits() {
        return debits;
    }

    public Set<CreditEntry> getCredits() {
        return credits;
    }

    public void addToDebits(DebitEntry debitEntry){
        if( null != debitEntry) {
            this.debits.add(debitEntry);
        }
    }
    public void addToCredits(CreditEntry creditEntry){
        if( null != creditEntry){
            this.credits.add(creditEntry);
        }
    }

}
