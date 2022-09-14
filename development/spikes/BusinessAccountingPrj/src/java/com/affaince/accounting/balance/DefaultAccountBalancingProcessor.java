package com.affaince.accounting.balance;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.DebitLedgerEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import org.joda.time.LocalDateTime;

import java.util.Set;

public class DefaultAccountBalancingProcessor implements AccountBalancingProcessor{
    @Override
    public LedgerAccount balanceAccount(LedgerAccount ledgerAccount,LocalDateTime closureDate) {
        LedgerAccount ledgerAccountCurrentVersion = (LedgerAccount) ledgerAccount.clone();
        LedgerAccount closedLedgerAccount = closeCurrentAccount(ledgerAccount,closureDate);
        ledgerAccountCurrentVersion.setStartDate(closureDate.plusMinutes(1));
        DebitLedgerEntry openingDebitLedgerEntry=null;
        CreditLedgerEntry openingCreditLedgerEntry=null;
        ledgerAccountCurrentVersion.flushAllEntries();
        LedgerAccountEntry closingDebitLedgerEntry = closedLedgerAccount.getDebits().stream().filter(cla->cla.getPeerAccountNumber().equals("toBalanceCarriedDown")).findAny().orElse(null);
        LedgerAccountEntry closingCreditLedgerEntry = closedLedgerAccount.getCredits().stream().filter(cla->cla.getPeerAccountNumber().equals("byBalanceCarriedDown")).findAny().orElse(null);

        if(null != closingDebitLedgerEntry) {
            openingCreditLedgerEntry = new CreditLedgerEntry(ledgerAccount.getClosureDate().plusSeconds(10),"byBalanceBroughtDown",AccountIdentifier.BY_BALANCE_BROUGHT_DOWN,null, closingDebitLedgerEntry.getAmount());
        }
        if(null != closingCreditLedgerEntry) {
            openingDebitLedgerEntry = new DebitLedgerEntry(ledgerAccount.getClosureDate().plusSeconds(10),"toBalanceBroughtDown",AccountIdentifier.TO_BALANCE_BROUGHT_DOWN,null, closingCreditLedgerEntry.getAmount());
        }

       // LedgerAccount ledgerAccountCurrentVersion = (LedgerAccount) ledgerAccount.clone();
        if( null != openingDebitLedgerEntry) {
            ledgerAccountCurrentVersion.debit(openingDebitLedgerEntry);
        }
        if(null != openingCreditLedgerEntry) {
            ledgerAccountCurrentVersion.credit(openingCreditLedgerEntry);
        }
        ledgerAccountCurrentVersion.setLatestVersion(true);
        AccountDatabaseSimulator.addAccount(ledgerAccountCurrentVersion);
        return ledgerAccountCurrentVersion;
    }

    private LedgerAccount closeCurrentAccount(LedgerAccount ledgerAccount,LocalDateTime closureDate){
        double sumOfDebits = sumOnDebitSide(ledgerAccount);
        double sumOfCredits = sumOnCreditSide(ledgerAccount);

        CreditLedgerEntry  closingCreditLedgerEntry;
        DebitLedgerEntry  closingDebitLedgerEntry;

        if(sumOfDebits > sumOfCredits){
            closingCreditLedgerEntry = new CreditLedgerEntry(closureDate,"byBalanceCarriedDown", AccountIdentifier.BY_BALANCE_CARRIED_DOWN,null,sumOfDebits-sumOfCredits);
            ledgerAccount.credit(closingCreditLedgerEntry);
        }else if(sumOfCredits > sumOfDebits){
            closingDebitLedgerEntry = new DebitLedgerEntry(closureDate,"toBalanceCarriedDown", AccountIdentifier.TO_BALANCE_CARRIED_DOWN,null,sumOfCredits-sumOfDebits);
            ledgerAccount.debit(closingDebitLedgerEntry);
        }else {
            //what to do?
        }

        LedgerAccount latestClosedLedgerAccount = AccountDatabaseSimulator.getLatestClosedAccount(ledgerAccount.getMerchantId(), ledgerAccount.getAccountId());
        if(null != latestClosedLedgerAccount) {
            latestClosedLedgerAccount.setLatestVersion(false);
        }
        ledgerAccount.closeActiveVersion(closureDate);
        return ledgerAccount;
    }
    private double sumOnDebitSide(LedgerAccount ledgerAccount){
        Set<LedgerAccountEntry> debitEntries = ledgerAccount.getDebits();
        double sumOfDebits=0;
        for(LedgerAccountEntry debitLedgerEntry: debitEntries){
            sumOfDebits +=debitLedgerEntry.getAmount();
        }
        return sumOfDebits;
    }

    private double sumOnCreditSide(LedgerAccount ledgerAccount){
        Set<LedgerAccountEntry> creditEntries = ledgerAccount.getCredits();
        double sumOfCredits=0;
        for(LedgerAccountEntry creditLedgerEntry: creditEntries){
            sumOfCredits +=creditLedgerEntry.getAmount();
        }
        return sumOfCredits;

    }
}
