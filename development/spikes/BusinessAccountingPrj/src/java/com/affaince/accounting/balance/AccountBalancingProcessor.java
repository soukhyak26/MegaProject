package com.affaince.accounting.balance;

import com.affaince.accounting.ledger.accounts.LedgerAccount;

public interface AccountBalancingProcessor {
    LedgerAccount balanceAccount(LedgerAccount ledgerAccount);
}
