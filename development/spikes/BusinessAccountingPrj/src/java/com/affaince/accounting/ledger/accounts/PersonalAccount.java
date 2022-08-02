package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class PersonalAccount extends AbstractLedgerAccountStereoType {
    public PersonalAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier) {
        super(merchantId,accountId,accountIdentifier);
    }

}
