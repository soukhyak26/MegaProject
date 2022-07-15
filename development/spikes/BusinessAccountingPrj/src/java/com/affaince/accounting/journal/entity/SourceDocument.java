package com.affaince.accounting.journal.entity;

import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import org.joda.time.LocalDateTime;

import com.affaince.accounting.journal.qualifiers.NatureOfTransaction;

import static java.util.Objects.requireNonNull;

public class SourceDocument {
    private String transactionReferenceNumber;
    private LocalDateTime dateOfTransaction;
    private double transactionAmount ;
    private NatureOfTransaction natureOfTransaction;
    private TransactionEntityDetail transactionEntityDetail;
    private Party party;
    private ModeOfTransaction modeOfTransaction;


    public static class SourceDocumentBuilder {
        String transactionReferenceNumber;
        LocalDateTime dateOfTransaction;
        double transactionAmount ;
        NatureOfTransaction natureOfTransaction;
        TransactionEntityDetail transactionEntityDetail;
        Party party;
        ModeOfTransaction modeOfTransaction;

        public SourceDocumentBuilder transactionReferenceNumber(String transactionReferenceNumber) {
            requireNonNull(transactionReferenceNumber);
            this.transactionReferenceNumber = transactionReferenceNumber;
            return this;
        }


        public SourceDocumentBuilder dateOfTransaction(LocalDateTime dateOfTransaction) {
            requireNonNull(dateOfTransaction);
            this.dateOfTransaction=dateOfTransaction;
            return this;
        }


        public SourceDocumentBuilder transactionAmount(double transactionAmount) {
            requireNonNull(transactionAmount);
            this.transactionAmount=transactionAmount;
            return this;
        }


        public SourceDocumentBuilder natureOfTransaction(NatureOfTransaction natureOfTransaction) {
            requireNonNull(natureOfTransaction);
            this.natureOfTransaction=natureOfTransaction;
            return this;

        }

        public SourceDocumentBuilder transactionEntityDetail(String itemId, double ratePerUnit, double quantity, String supplierId, double discountInPercent, String description) {
            this.transactionEntityDetail = new TransactionEntityDetail(itemId, ratePerUnit, quantity, supplierId, discountInPercent, description);
            return this;

        }

        public SourceDocumentBuilder party(String partyId, PartyTypes partyType, ExchangeableItems itemExchanged, double amountExchanged) {
            requireNonNull(partyId);
            requireNonNull(partyType);
            requireNonNull(itemExchanged);
            requireNonNull(amountExchanged);
            this.party = new Party(partyId, partyType, itemExchanged, amountExchanged);
            return this;

        }

        public SourceDocumentBuilder modeOfTransaction(ModeOfTransaction modeOfTransaction) {
            requireNonNull(modeOfTransaction);
            this.modeOfTransaction=modeOfTransaction;
            return this;
        }

        public SourceDocument build() {
            return SourceDocument.create(this);
        }
    }

    public static SourceDocumentBuilder newBuilder(){
        return new SourceDocumentBuilder();
    }

    private SourceDocument(SourceDocumentBuilder sourceDocumentBuilder){
        this.transactionReferenceNumber=sourceDocumentBuilder.transactionReferenceNumber;
        this.dateOfTransaction=sourceDocumentBuilder.dateOfTransaction;
        this.modeOfTransaction=sourceDocumentBuilder.modeOfTransaction;
        this.natureOfTransaction= sourceDocumentBuilder.natureOfTransaction;
        this.party=sourceDocumentBuilder.party;
        this.transactionAmount=sourceDocumentBuilder.transactionAmount;
        this.transactionEntityDetail=sourceDocumentBuilder.transactionEntityDetail;
    }
    public static SourceDocument create(SourceDocumentBuilder sourceDocumentBuilder){
        return new SourceDocument(sourceDocumentBuilder);
    }
}
