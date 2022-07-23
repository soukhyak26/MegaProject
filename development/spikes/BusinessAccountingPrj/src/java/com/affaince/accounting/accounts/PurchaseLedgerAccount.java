package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.RealAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class PurchaseLedgerAccount extends AbstractLedgerAccountStereoType implements RealAccount {
    public PurchaseLedgerAccount(String accountId, AccountIdentifier accountIdentifier) {
        super(accountId,accountIdentifier);
    }
}
