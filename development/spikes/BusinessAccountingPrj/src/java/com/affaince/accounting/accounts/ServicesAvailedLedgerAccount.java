package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.NominalAccount;
import com.affaince.accounting.accounts.types.RealAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class ServicesAvailedLedgerAccount extends AbstractLedgerAccountStereoType implements RealAccount {
    public ServicesAvailedLedgerAccount(String accountId, AccountIdentifier accountIdentifier) {
        super(accountId,accountIdentifier);
    }
}
