package com.affaince.accounting.ledger.accounts;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class TradingAccount extends AbstractLedgerAccountStereoType{
    public TradingAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        super(merchantId, accountId, accountIdentifier,startDate,closureDate);
        super.startDate = startDate;
        super.closureDate=closureDate;
    }

    @Override
    public String toString() {
        return "TradingAccount{} " + super.toString();
    }
}
