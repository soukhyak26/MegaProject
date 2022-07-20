package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.NominalAccount;

public class SalesAccount extends AbstractLedgerAccountStereoType implements NominalAccount {
    public SalesAccount(String accountId) {
        super(accountId);
    }

}
