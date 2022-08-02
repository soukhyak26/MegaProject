package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class RealAccount extends AbstractLedgerAccountStereoType{
    public RealAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier) {
        super(merchantId,accountId,accountIdentifier);
    }

}
