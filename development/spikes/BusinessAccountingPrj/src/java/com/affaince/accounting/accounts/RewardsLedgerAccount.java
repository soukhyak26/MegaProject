package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.NominalAccount;

public class RewardsLedgerAccount extends AbstractLedgerAccountStereoType implements NominalAccount {
    public RewardsLedgerAccount(String accountId) {
        super(accountId);
    }
}
