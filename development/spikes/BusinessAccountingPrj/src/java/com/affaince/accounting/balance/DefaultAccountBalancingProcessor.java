package com.affaince.accounting.balance;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.DebitLedgerEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;

import java.util.Set;

public class DefaultAccountBalancingProcessor implements AccountBalancingProcessor{
    @Override
    public LedgerAccount balanceAccount(LedgerAccount ledgerAccount) {
        double sumOfDebits = sumOnDebitSide(ledgerAccount);
        double sumOfCredits = sumOnCreditSide(ledgerAccount);
        if(sumOfDebits > sumOfCredits){
            CreditLedgerEntry  creditLedgerEntry = new CreditLedgerEntry(LocalDateTime.now(),"byBalanceCarriedDown", AccountIdentifier.BY_BALANCE_CARRIED_DOWN,null,sumOfDebits-sumOfCredits);
            ledgerAccount.credit(creditLedgerEntry);
        }else if(sumOfCredits > sumOfDebits){
            DebitLedgerEntry  debitLedgerEntry = new DebitLedgerEntry(LocalDateTime.now(),"TOBalanceCarriedDown", AccountIdentifier.TO_BALANCE_CARRIED_DOWN,null,sumOfCredits-sumOfDebits);
            ledgerAccount.debit(debitLedgerEntry);
        }else {
            //what to do?
        }
        return ledgerAccount;
    }

    private double sumOnDebitSide(LedgerAccount ledgerAccount){
        Set<DebitLedgerEntry> debitEntries = ledgerAccount.getDebits();
        double sumOfDebits=0;
        for(DebitLedgerEntry debitLedgerEntry: debitEntries){
            sumOfDebits +=debitLedgerEntry.getAmount();
        }
        return sumOfDebits;
    }

    private double sumOnCreditSide(LedgerAccount ledgerAccount){
        Set<CreditLedgerEntry> creditEntries = ledgerAccount.getCredits();
        double sumOfCredits=0;
        for(CreditLedgerEntry creditLedgerEntry: creditEntries){
            sumOfCredits +=creditLedgerEntry.getAmount();
        }
        return sumOfCredits;

    }
}
