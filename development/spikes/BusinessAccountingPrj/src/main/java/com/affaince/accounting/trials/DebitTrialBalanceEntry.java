package com.affaince.accounting.trials;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class DebitTrialBalanceEntry extends TrialBalanceEntry{
    public DebitTrialBalanceEntry(String accountId, AccountIdentifier accountIdentifier,String peerAccountId,AccountIdentifier peerAccountIdentifier,String ledgerFolio, double balanceAmount, NatureOfBalance natureOfBalance) {
        super(accountId, accountIdentifier,peerAccountId,peerAccountIdentifier,ledgerFolio, balanceAmount, natureOfBalance);
    }

    @Override
    public String toString() {
        return "\nDebitTrialBalanceEntry{} " + super.toString();
    }
}
