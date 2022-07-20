package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.PersonalAccount;
import com.affaince.accounting.journal.qualifiers.AccountQualifiers;

public class BankLedgerAccount extends AbstractLedgerAccountStereoType implements PersonalAccount {
    public BankLedgerAccount(String accountId) {
        super(accountId);
    }
}
