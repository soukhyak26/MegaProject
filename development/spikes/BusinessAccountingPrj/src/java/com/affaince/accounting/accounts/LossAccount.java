package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.NominalAccount;

public class LossAccount extends AbstractLedgerAccountStereoType implements NominalAccount {
    public LossAccount(String accountId) {
        super(accountId);
    }
}
