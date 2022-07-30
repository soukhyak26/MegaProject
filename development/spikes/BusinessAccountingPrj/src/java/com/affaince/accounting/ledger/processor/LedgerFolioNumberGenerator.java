package com.affaince.accounting.ledger.processor;

import java.util.List;
import java.util.UUID;

public class LedgerFolioNumberGenerator {

    public static String generateLedgerFolioNumber(String merchantId,List<String> debitAccountIds, List<String> creditAccountIds){
        String ledgerFolioNumber=merchantId + "@";
        for(String debitAccountId : debitAccountIds){
            ledgerFolioNumber +=UUID.fromString(debitAccountId);
            ledgerFolioNumber +="#";
        }
        ledgerFolioNumber = ledgerFolioNumber.substring(0,ledgerFolioNumber.lastIndexOf("#"));
        ledgerFolioNumber +=ledgerFolioNumber + "$";
        for(String creditAccountId : creditAccountIds) {
            ledgerFolioNumber += UUID.fromString(creditAccountId);
            ledgerFolioNumber +="#";
        }
        ledgerFolioNumber = ledgerFolioNumber.substring(0,ledgerFolioNumber.lastIndexOf("#"));
        return ledgerFolioNumber;
    }
}
