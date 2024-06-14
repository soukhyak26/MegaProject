package com.affaince.subscription.accounting.web.controller;

import com.affaince.subscription.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.subscription.accounting.transactions.SourceDocument;
import com.affaince.subscription.accounting.web.request.AccountingTransactionRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class SourceDocumentTransformer {

    public SourceDocument transform(AccountingTransactionRequest request) {
        String transactionRefNumber = UUID.randomUUID().toString();
        SourceDocument.SourceDocumentBuilder builder = SourceDocument.newBuilder()
                .merchantId(request.getMerchantId())
                .transactionReferenceNumber(transactionRefNumber)
                .transactionAmount(request.getTransactionAmount())
                .dateOfTransaction(request.getDateOfTransaction());
        if (request.isTransactionOnCredit()) {
            builder.modeOfTransaction(ModeOfTransaction.ON_CREDIT);
        } else {
            builder.modeOfTransaction(ModeOfTransaction.BY_PAYMENT);
        }
        builder.transactionEvent(request.getAccountingEvent())
                .giverParticipant(request.getGiverPartyId(), request.getGiverParticipantType(), request.getExchangeableItem(), request.getGiverAmount())
                .receiverParticipant(request.getReceiverPartyId(), request.getGiverParticipantType(), request.getExchangeableItem(), request.getReceiverAmount())
                .description("capital invested")
                .build();
        return builder.build();
    }
}