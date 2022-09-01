package com.affaince.accounting.balance;

import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;

public interface AccountBalancingProcessor {
    LedgerAccount balanceAccount(LedgerAccount ledgerAccount, LocalDateTime closureDate);
}
