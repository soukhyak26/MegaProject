package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class NominalAccount extends AbstractLedgerAccountStereoType {
    public NominalAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier) {
        super(merchantId, accountId, accountIdentifier);
    }
}
