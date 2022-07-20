package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.NominalAccount;

public class DiscountLedgerAccount extends AbstractLedgerAccountStereoType implements NominalAccount {
    public DiscountLedgerAccount(String accountId) {
        super(accountId);
    }
}
