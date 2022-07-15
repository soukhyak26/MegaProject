package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.RealAccount;

public class PurchaseLedgerAccount extends AbstractLedgerAccount implements RealAccount {
    public PurchaseLedgerAccount(String accountId) {
        super(accountId);
    }
}
