package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.ledger.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.ledger.accounts.types.RealAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class ServicesAvailedLedgerAccount extends AbstractLedgerAccountStereoType implements RealAccount {
    public ServicesAvailedLedgerAccount(String merchantId,String accountId, AccountIdentifier accountIdentifier) {
        super(merchantId,accountId,accountIdentifier);
    }
}