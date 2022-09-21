package com.affaince.accounting.balance;

import com.affaince.accounting.db.AccountDatabaseSimulator;
import com.affaince.accounting.ids.DefaultIdGenerator;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.CreditLedgerEntry;
import com.affaince.accounting.ledger.accounts.DebitLedgerEntry;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DefaultAccountBalancingProcessor implements AccountBalancingProcessor{
    private final AccountDatabaseSimulator accountDatabaseSimulator;
    private final DefaultIdGenerator defaultIdGenerator;

    public DefaultAccountBalancingProcessor(AccountDatabaseSimulator accountDatabaseSimulator, DefaultIdGenerator defaultIdGenerator) {
        this.accountDatabaseSimulator = accountDatabaseSimulator;
        this.defaultIdGenerator = defaultIdGenerator;
    }

    @Override
    public LedgerAccount closeAccount(LedgerAccount ledgerAccount, LocalDateTime startDate, LocalDateTime closureDate) {
        return closeCurrentAccount(ledgerAccount,startDate,closureDate);
    }

    public LedgerAccount openAccount(LedgerAccount ledgerAccount,LocalDateTime startDate,LocalDateTime closureDate){
        LedgerAccount closedLedgerAccount = accountDatabaseSimulator.getLatestClosedAccount(ledgerAccount.getMerchantId(),ledgerAccount.getAccountId(),ledgerAccount.getStartDate(),ledgerAccount.getClosureDate());
        if(null != closedLedgerAccount) {
            LedgerAccountEntry closingDebitLedgerEntry = closedLedgerAccount.getDebits().stream().filter(cla -> cla.getPeerAccountNumber().equals("toBalanceCarriedDown")).findAny().orElse(null);
            LedgerAccountEntry closingCreditLedgerEntry = closedLedgerAccount.getCredits().stream().filter(cla -> cla.getPeerAccountNumber().equals("byBalanceCarriedDown")).findAny().orElse(null);

            DebitLedgerEntry openingDebitLedgerEntry = null;
            CreditLedgerEntry openingCreditLedgerEntry = null;
            if (null != closingDebitLedgerEntry) {
                openingCreditLedgerEntry = new CreditLedgerEntry(ledgerAccount.getClosureDate(), "byBalanceBroughtDown", AccountIdentifier.BY_BALANCE_BROUGHT_DOWN, null, closingDebitLedgerEntry.getLedgerFolio(), closingDebitLedgerEntry.getAmount());
            }
            if (null != closingCreditLedgerEntry) {
                openingDebitLedgerEntry = new DebitLedgerEntry(ledgerAccount.getClosureDate(), "toBalanceBroughtDown", AccountIdentifier.TO_BALANCE_BROUGHT_DOWN, null, closingCreditLedgerEntry.getLedgerFolio(), closingCreditLedgerEntry.getAmount());
            }

            // LedgerAccount ledgerAccountCurrentVersion = (LedgerAccount) ledgerAccount.clone();
            if (null != openingDebitLedgerEntry) {
                ledgerAccount.debit(openingDebitLedgerEntry);
            }
            if (null != openingCreditLedgerEntry) {
                ledgerAccount.credit(openingCreditLedgerEntry);
            }
            ledgerAccount.setLatestVersion(true);
        }
        return ledgerAccount;
    }

    private LedgerAccount closeCurrentAccount(LedgerAccount ledgerAccount,LocalDateTime startDate,LocalDateTime closureDate){
        double sumOfDebits = sumOnDebitSide(ledgerAccount);
        double sumOfCredits = sumOnCreditSide(ledgerAccount);

        CreditLedgerEntry  closingCreditLedgerEntry;
        DebitLedgerEntry  closingDebitLedgerEntry;
        String ledgerFolio = defaultIdGenerator.generator(ledgerAccount.getMerchantId()+"$" +ledgerAccount.getAccountId() + "$" +  ledgerAccount.getAccountIdentifier() +"$" + closureDate);
        if(sumOfDebits > sumOfCredits){
            closingCreditLedgerEntry = new CreditLedgerEntry(closureDate,"byBalanceCarriedDown", AccountIdentifier.BY_BALANCE_CARRIED_DOWN,null,ledgerFolio,sumOfDebits-sumOfCredits);
            ledgerAccount.credit(closingCreditLedgerEntry);
        }else if(sumOfCredits > sumOfDebits){
            closingDebitLedgerEntry = new DebitLedgerEntry(closureDate,"toBalanceCarriedDown", AccountIdentifier.TO_BALANCE_CARRIED_DOWN,null,ledgerFolio,sumOfCredits-sumOfDebits);
            ledgerAccount.debit(closingDebitLedgerEntry);
        }else {
            //what to do?
        }

        LedgerAccount latestClosedLedgerAccount = accountDatabaseSimulator.getLatestClosedAccount(ledgerAccount.getMerchantId(), ledgerAccount.getAccountId(),startDate,closureDate);
        if(null != latestClosedLedgerAccount) {
            latestClosedLedgerAccount.setLatestVersion(false);
        }
        return ledgerAccount;
    }
    private double sumOnDebitSide(LedgerAccount ledgerAccount){
        List<LedgerAccountEntry> debitEntries = ledgerAccount.getDebits();
        double sumOfDebits=0;
        for(LedgerAccountEntry debitLedgerEntry: debitEntries){
            sumOfDebits +=debitLedgerEntry.getAmount();
        }
        return sumOfDebits;
    }

    private double sumOnCreditSide(LedgerAccount ledgerAccount){
        List<LedgerAccountEntry> creditEntries = ledgerAccount.getCredits();
        double sumOfCredits=0;
        for(LedgerAccountEntry creditLedgerEntry: creditEntries){
            sumOfCredits +=creditLedgerEntry.getAmount();
        }
        return sumOfCredits;

    }
}
