package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.RealAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class FurnitureLedgerAccount extends AbstractLedgerAccountStereoType implements RealAccount {
    public FurnitureLedgerAccount(String accountId, AccountIdentifier accountIdentifier) {
        super(accountId,accountIdentifier);
    }
}
