package com.affaince.accounting.stock;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.NominalAccount;
import org.joda.time.LocalDateTime;

public class ClosingStockAccount extends NominalAccount {

    public ClosingStockAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        super(merchantId,accountId,accountIdentifier,startDate,closureDate);
    }

}
