package com.affaince.accounting.balance;

import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;

public interface AccountBalancingProcessor {
    LedgerAccount closeAccount(LedgerAccount ledgerAccount, LocalDateTime startDate, LocalDateTime closureDate);
    LedgerAccount openAccount(LedgerAccount ledgerAccount, LocalDateTime startDate, LocalDateTime closureDate);
}
