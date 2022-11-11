package com.affaince.accounting.trials;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;

public class CreditTrialBalanceEntry extends TrialBalanceEntry {
    public CreditTrialBalanceEntry(String accountId, AccountIdentifier accountIdentifier,String peerAccountId,AccountIdentifier peerAccountIdentifier, String ledgerFolio,double balanceAmount, NatureOfBalance natureOfBalance) {
        super(accountId, accountIdentifier,peerAccountId,peerAccountIdentifier, ledgerFolio,balanceAmount,natureOfBalance);
    }

    @Override
    public String toString() {
        return "\nCreditTrialBalanceEntry{} " + super.toString();
    }
}
