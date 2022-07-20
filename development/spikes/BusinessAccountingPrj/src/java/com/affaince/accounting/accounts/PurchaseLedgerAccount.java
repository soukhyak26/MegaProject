package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.RealAccount;

public class PurchaseLedgerAccount extends AbstractLedgerAccountStereoType implements RealAccount {
    public PurchaseLedgerAccount(String accountId) {
        super(accountId);
    }
}
