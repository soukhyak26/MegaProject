package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

import java.util.Set;
import java.util.Stack;

public interface LedgerAccount extends Cloneable {
     String getMerchantId();
     String getAccountId();
     AccountIdentifier getAccountIdentifier();
     void debit(DebitLedgerEntry debitEntry);
     void credit(CreditLedgerEntry creditEntry);
     Set<DebitLedgerEntry> getDebits();
     Set<CreditLedgerEntry> getCredits();
     LocalDateTime getClosureDate();
     void closeActiveVersion(LocalDateTime closureDate);
     void flushAllEntries();
     void setLatestVersion(boolean isLatestVersion);
     boolean getLatestVersion();
     Object clone();
}
