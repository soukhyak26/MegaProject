package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.RealAccount;

public class CashLedgerAccount extends AbstractLedgerAccountStereoType implements RealAccount {
    public CashLedgerAccount(String accountId) {
        super(accountId);
    }
}
