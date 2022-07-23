package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.NominalAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class LossAccount extends AbstractLedgerAccountStereoType implements NominalAccount {
    public LossAccount(String accountId, AccountIdentifier accountIdentifier) {
        super(accountId,accountIdentifier);
    }
}
