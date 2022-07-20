package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.RealAccount;

public class FurnitureLedgerAccount extends AbstractLedgerAccountStereoType implements RealAccount {
    public FurnitureLedgerAccount(String accountId) {
        super(accountId);
    }
}
