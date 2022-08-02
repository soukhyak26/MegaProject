package com.affaince.accounting.balance;

import com.affaince.accounting.ledger.accounts.types.LedgerAccount;

public interface AccountBalancingProcessor {
    LedgerAccount balanceAccount(LedgerAccount ledgerAccount);
}
