package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.ledger.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.ledger.accounts.types.NominalAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class ProfitAccount extends AbstractLedgerAccountStereoType implements NominalAccount {
    public ProfitAccount(String merchantId,String accountId, AccountIdentifier accountIdentifier) {
        super(merchantId,accountId,accountIdentifier);
    }
}
