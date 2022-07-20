package com.affaince.accounting.journal.entity;

import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import org.joda.time.LocalDateTime;

import com.affaince.accounting.journal.qualifiers.TransactionEvents;

import static java.util.Objects.requireNonNull;
//Shall we add two parties..giver and receiver.
//Currently source document assumes that all the transaction happen between external stakeholders and business
//So one of the party is business

public class SourceDocument {
    private String transactionReferenceNumber;
    private LocalDateTime dateOfTransaction;
    private double transactionAmount ;
    private TransactionEvents transactionEvents;
    private TransactionEntityDetail transactionEntityDetail;
    private Party giverParty;
    private Party receiverParty;
    private ModeOfTransaction modeOfTransaction;
    private String description;


    public static class SourceDocumentBuilder {
        String transactionReferenceNumber;
        LocalDateTime dateOfTransaction;
        double transactionAmount ;
        TransactionEvents transactionEvents;
        TransactionEntityDetail transactionEntityDetail;
        private Party giverParty;
        private Party receiverParty;
        ModeOfTransaction modeOfTransaction;
        private String description;

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


        public SourceDocumentBuilder natureOfTransaction(TransactionEvents transactionEvents) {
            requireNonNull(transactionEvents);
            this.transactionEvents = transactionEvents;
            return this;

        }

        public SourceDocumentBuilder transactionEntityDetail(String entityId,double ratePerUnit, double quantity, String supplierId, double discountInPercent, String description) {
            this.transactionEntityDetail = new TransactionEntityDetail(entityId, ratePerUnit, quantity, supplierId, discountInPercent, description);
            return this;

        }

        public SourceDocumentBuilder giverParty(String partyId, PartyTypes partyType, ExchangeableItems itemExchanged, double amountExchanged) {
            requireNonNull(partyId);
            requireNonNull(partyType);
            requireNonNull(itemExchanged);
            requireNonNull(amountExchanged);
            this.giverParty = new Party(partyId, partyType, itemExchanged, amountExchanged);
            return this;

        }

        public SourceDocumentBuilder receiverParty(String partyId, PartyTypes partyType, ExchangeableItems itemExchanged, double amountExchanged) {
            requireNonNull(partyId);
            requireNonNull(partyType);
            requireNonNull(itemExchanged);
            requireNonNull(amountExchanged);
            this.receiverParty = new Party(partyId, partyType, itemExchanged, amountExchanged);
            return this;

        }

        public SourceDocumentBuilder modeOfTransaction(ModeOfTransaction modeOfTransaction) {
            requireNonNull(modeOfTransaction);
            this.modeOfTransaction=modeOfTransaction;
            return this;
        }
        public SourceDocumentBuilder description(String description){
            requireNonNull(description);
            this.description=description;
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
        this.transactionEvents = sourceDocumentBuilder.transactionEvents;
        this.giverParty=sourceDocumentBuilder.giverParty;
        this.receiverParty=sourceDocumentBuilder.receiverParty;
        this.transactionAmount=sourceDocumentBuilder.transactionAmount;
        this.transactionEntityDetail=sourceDocumentBuilder.transactionEntityDetail;
    }
    public static SourceDocument create(SourceDocumentBuilder sourceDocumentBuilder){
        return new SourceDocument(sourceDocumentBuilder);
    }

    public String getTransactionReferenceNumber() {
        return transactionReferenceNumber;
    }

    public LocalDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public TransactionEvents getNatureOfTransaction() {
        return transactionEvents;
    }

    public TransactionEntityDetail getTransactionEntityDetail() {
        return transactionEntityDetail;
    }

    public Party getGiverParty() {
        return giverParty;
    }

    public Party getReceiverParty() {
        return receiverParty;
    }

    public ModeOfTransaction getModeOfTransaction() {
        return modeOfTransaction;
    }

    public String getDescription() {
        return description;
    }
}
