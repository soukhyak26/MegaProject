package com.affaince.subscription.accounting.balance;

import com.affaince.subscription.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;

public interface AccountBalancingProcessor {
    LedgerAccount closeAccount(LedgerAccount ledgerAccount, LocalDateTime startDate, LocalDateTime closureDate);
    LedgerAccount openAccount(LedgerAccount ledgerAccount, LocalDateTime startDate, LocalDateTime closureDate);
}
