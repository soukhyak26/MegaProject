package com.affaince.accounting.journal.entity;

import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import org.joda.time.LocalDateTime;

import com.affaince.accounting.journal.qualifiers.NatureOfTransaction;

public class SourceDocument {
    private String transactionReferenceNumber;
    private LocalDateTime dateOfTransaction;
    private double transactionAmount ;
    private NatureOfTransaction natureOfTransaction;
    private TransactionEntityDetail transactionEntityDetail;
    private Party party;
    private ModeOfTransaction modeOfTransaction;

}
