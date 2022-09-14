package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

import java.util.Set;

public interface LedgerAccount extends Cloneable {
     String getMerchantId();
     String getAccountId();
     AccountIdentifier getAccountIdentifier();
     void debit(LedgerAccountEntry debitEntry);
     void credit(LedgerAccountEntry creditEntry);
     Set<LedgerAccountEntry> getDebits();
     Set<LedgerAccountEntry> getCredits();
     LocalDateTime getStartDate();
     void setStartDate(LocalDateTime startDate);
     LocalDateTime getClosureDate();
     void closeActiveVersion(LocalDateTime closureDate);
     void flushAllEntries();
     void setLatestVersion(boolean isLatestVersion);
     boolean getLatestVersion();
     Object clone();
}
