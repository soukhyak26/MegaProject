package com.affaince.accounting.ledger.accounts.types;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

import java.util.Comparator;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class AbstractLedgerAccountStereoType implements LedgerAccount{
    private final String merchantId;
    private final String accountId;
    private final AccountIdentifier accountIdentifier;
    private Set<DebitLedgerEntry> debits;
    private Set<CreditLedgerEntry> credits;
    private Stack<LedgerAccount> previousVersions;


    public AbstractLedgerAccountStereoType(String merchantId,String accountId,AccountIdentifier accountIdentifier) {
        this.merchantId=merchantId;
        this.accountId= accountId;
        this.accountIdentifier = accountIdentifier;
        this.debits = new TreeSet<>(Comparator.comparing(LedgerAccountEntry::getDate));
        this.credits = new TreeSet<>(Comparator.comparing(LedgerAccountEntry::getDate));
        this.previousVersions= new Stack<>();
    }

    @Override
    public Object clone()  {
            try {
                LedgerAccount cloned = (LedgerAccount) super.clone();
                Set<DebitLedgerEntry> debitsClone = debits.stream().map(debitEntry -> (DebitLedgerEntry) debitEntry.clone()).collect(Collectors.toSet());
                Set<CreditLedgerEntry> creditsClone = credits.stream().map(creditEntry -> (CreditLedgerEntry) creditEntry.clone()).collect(Collectors.toSet());
                ((AbstractLedgerAccountStereoType) cloned).setClone(debitsClone, creditsClone);
                return cloned;
            }catch (CloneNotSupportedException ex){
                ex.printStackTrace();
            }
            return null;
    }
    public void flushLedgerAccountEntries() {
        this.credits.removeAll(this.credits);
        this.debits.removeAll(this.debits);
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

    public void debit(DebitLedgerEntry debitEntry){
        if( null != debitEntry) {
            this.debits.add(debitEntry);
        }
    }
    public void credit(CreditLedgerEntry creditEntry){
        if( null != creditEntry){
            this.credits.add(creditEntry);
        }
    }

    public void addPreviousVersion(LedgerAccount ledgerAccount){
        previousVersions.push(ledgerAccount);
    }

    public Stack<LedgerAccount> getPreviousVersions(){
        return this.previousVersions;
    }

    public void setClone(Set<DebitLedgerEntry> debitsCloned, Set<CreditLedgerEntry> creditsCloned){
        this.debits=debitsCloned;
        this.credits = creditsCloned;
    }
    @Override
    public String toString() {
        return "AbstractLedgerAccountStereoType{" +
                "merchantId='" + merchantId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", debits=" + debits +
                ", credits=" + credits +
                '}';
    }
}
