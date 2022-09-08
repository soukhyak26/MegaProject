package com.affaince.accounting.trading;

import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import com.affaince.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;

public interface TradingAccountPostingProcessor {

    LedgerAccount postToTradingAccount(String merchantId,
                                       LocalDateTime postingDate,
                                       TradingFrequency tradingFrequency);
}
