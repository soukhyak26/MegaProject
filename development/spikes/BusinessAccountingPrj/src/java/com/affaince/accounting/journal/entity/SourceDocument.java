package com.affaince.accounting.journal.entity;

import com.affaince.accounting.journal.qualifiers.ExchangeableItems;
import com.affaince.accounting.journal.qualifiers.ModeOfTransaction;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import org.joda.time.LocalDateTime;

import com.affaince.accounting.journal.qualifiers.TransactionEvents;

import static java.util.Objects.requireNonNull;
//SourceDocument assumes that any transaction either brings money to the business or sends money by the business to some external stakeholder.
//So it should either have a giver or the receiver as the business. It is system's responsibility to identify the representative business account.
// representative business account can be cash, bank account, discount, profit/loss account, reward etc.
// For each type of event the possible BUSINESS REPRESENTATIVES should be provided by the respective Event Processor.
//When one of the party is BUSINESS, the opposite Party will be an external stakeholder.
//Stakeholders are identified as supplier,subscriber,service provider etc.
//The party except business should have the identifier of the stakeholder.

public class SourceDocument {
    private String merchantId;
    private String transactionReferenceNumber;
    private LocalDateTime dateOfTransaction;
    private double transactionAmount ;
    private TransactionEvents transactionEvent;
    private TransactionEntityDetail transactionEntityDetail;
    private Participant giverParticipant;
    private Participant receiverParticipant;
    private ModeOfTransaction modeOfTransaction;
    private String description;


    public static class SourceDocumentBuilder {
        private String merchantId;
        String transactionReferenceNumber;
        LocalDateTime dateOfTransaction;
        double transactionAmount ;
        TransactionEvents transactionEvent;
        TransactionEntityDetail transactionEntityDetail;
        private Participant giverParticipant;
        private Participant receiverParticipant;
        ModeOfTransaction modeOfTransaction;
        private String description;

        public SourceDocumentBuilder merchantId(String merchantId){
            requireNonNull(merchantId);
            this.merchantId = merchantId;
            return this;
        }

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


        public SourceDocumentBuilder transactionEvent(TransactionEvents transactionEvent) {
            requireNonNull(transactionEvent);
            this.transactionEvent = transactionEvent;
            return this;

        }

        public SourceDocumentBuilder transactionEntityDetail(String entityId,double ratePerUnit, double quantity, String supplierId, double discountInPercent, String description) {
            this.transactionEntityDetail = new TransactionEntityDetail(entityId, ratePerUnit, quantity, supplierId, discountInPercent, description);
            return this;

        }

        public SourceDocumentBuilder giverParticipant(String partyId, PartyTypes partyType,ExchangeableItems itemExchanged, double amountExchanged) {
            requireNonNull(merchantId);
            requireNonNull(partyId);
            requireNonNull(partyType);
            requireNonNull(itemExchanged);
            requireNonNull(amountExchanged);
            this.giverParticipant = new Participant(merchantId,partyId, partyType,itemExchanged, amountExchanged);
            return this;

        }

        public SourceDocumentBuilder receiverParticipant(String partyId, PartyTypes partyType,ExchangeableItems itemExchanged, double amountExchanged) {
            requireNonNull(merchantId);
            requireNonNull(partyId);
            requireNonNull(partyType);
            requireNonNull(itemExchanged);
            requireNonNull(amountExchanged);
            this.receiverParticipant = new Participant(merchantId,partyId, partyType,itemExchanged, amountExchanged);
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
        this.merchantId=sourceDocumentBuilder.merchantId;
        this.transactionReferenceNumber=sourceDocumentBuilder.transactionReferenceNumber;
        this.dateOfTransaction=sourceDocumentBuilder.dateOfTransaction;
        this.modeOfTransaction=sourceDocumentBuilder.modeOfTransaction;
        this.transactionEvent = sourceDocumentBuilder.transactionEvent;
        this.giverParticipant =sourceDocumentBuilder.giverParticipant;
        this.receiverParticipant =sourceDocumentBuilder.receiverParticipant;
        this.transactionAmount=sourceDocumentBuilder.transactionAmount;
        this.transactionEntityDetail=sourceDocumentBuilder.transactionEntityDetail;
        this.description=sourceDocumentBuilder.description;
    }
    public static SourceDocument create(SourceDocumentBuilder sourceDocumentBuilder){
        return new SourceDocument(sourceDocumentBuilder);
    }

    public String getMerchantId() {
        return merchantId;
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

    public TransactionEvents getTransactionEvent() {
        return transactionEvent;
    }

    public TransactionEntityDetail getTransactionEntityDetail() {
        return transactionEntityDetail;
    }

    public Participant getGiverParticipant() {
        return giverParticipant;
    }

    public Participant getReceiverParticipant() {
        return receiverParticipant;
    }

    public ModeOfTransaction getModeOfTransaction() {
        return modeOfTransaction;
    }

    public String getDescription() {
        return description;
    }


}