package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
//debit all expenses and losses and credit all income and gains.
public class NominalAccount extends AbstractLedgerAccountStereoType {
    public NominalAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier) {
        super(merchantId, accountId, accountIdentifier);
    }
}
