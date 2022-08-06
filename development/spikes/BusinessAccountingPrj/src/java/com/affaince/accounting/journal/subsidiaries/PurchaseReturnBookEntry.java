package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.PartyTypes;
import com.affaince.accounting.journal.qualifiers.PriceQualifiers;
import com.affaince.accounting.transactions.TransactionEntityDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseReturnBookEntry {
    private final String merchantId;
    private LocalDateTime dateOfCreditPurchaseReturn;
    private final String partyId;
    private final PartyTypes partyType;
    private List<TransactionEntityDetail> transactionEntityDetailList;
    private String debitNoteNumber;
    private String ledgerFolioNumber;
    private Map<PriceQualifiers,Double> itemisedPrices;
    private double totalPrice;
    private String remarks;

    public static class PurchaseReturnBookBuilder {
        private String merchantId;
        private LocalDateTime dateOfCreditPurchaseReturn;
        private String partyId;
        private PartyTypes partyType;
        private List<TransactionEntityDetail> transactionEntityDetailList;
        private String debitNoteNumber;
        private String ledgerFolioNumber;
        private Map<PriceQualifiers,Double> itemisedPrices;
        private double totalPrice;
        private String remarks;

        public PurchaseReturnBookBuilder(){
            this.transactionEntityDetailList= new ArrayList<>();
            this.itemisedPrices= new HashMap<>();
        }
        public PurchaseReturnBookEntry.PurchaseReturnBookBuilder merchantId(String merchantId){
            this.merchantId =merchantId;
            return this;
        }

        public PurchaseReturnBookEntry.PurchaseReturnBookBuilder dateOfCreditPurchaseReturn(LocalDateTime dateOfCreditPurchaseReturn){
            this.dateOfCreditPurchaseReturn =dateOfCreditPurchaseReturn;
            return this;
        }
        public PurchaseReturnBookEntry.PurchaseReturnBookBuilder partyId(String partyId){
            this.partyId=partyId;
            return this;
        }
        public PurchaseReturnBookEntry.PurchaseReturnBookBuilder partyType(PartyTypes partyType){
            this.partyType=partyType;
            return this;
        }

        public PurchaseReturnBookEntry.PurchaseReturnBookBuilder transactionEntityDetailList(List<TransactionEntityDetail> transactionEntityDetailList){
            this.transactionEntityDetailList=transactionEntityDetailList;
            return this;
        }

        public PurchaseReturnBookEntry.PurchaseReturnBookBuilder debitNoteNumber(String debitNoteNumber){
            this.debitNoteNumber =debitNoteNumber;
            return this;
        }

        public PurchaseReturnBookEntry.PurchaseReturnBookBuilder itemisedPrices(Map<PriceQualifiers,Double> itemisedPrices){
            this.itemisedPrices=itemisedPrices;
            return this;
        }

        public PurchaseReturnBookEntry.PurchaseReturnBookBuilder totalPrice(double totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public PurchaseReturnBookEntry.PurchaseReturnBookBuilder remarks(String remarks){
            this.remarks=remarks;
            return this;
        }

        public PurchaseReturnBookEntry build() {
            return PurchaseReturnBookEntry.create(this);
        }
    }


    public static PurchaseReturnBookEntry.PurchaseReturnBookBuilder newBuilder(){
        return new PurchaseReturnBookEntry.PurchaseReturnBookBuilder();
    }
    public static PurchaseReturnBookEntry create(PurchaseReturnBookEntry.PurchaseReturnBookBuilder purchaseReturnBookBuilder){
        return new PurchaseReturnBookEntry(purchaseReturnBookBuilder);
    }
    public PurchaseReturnBookEntry(PurchaseReturnBookEntry.PurchaseReturnBookBuilder purchaseReturnBookBuilder){
        merchantId=purchaseReturnBookBuilder.merchantId;
        dateOfCreditPurchaseReturn =purchaseReturnBookBuilder.dateOfCreditPurchaseReturn;
        partyId=purchaseReturnBookBuilder.partyId;
        partyType=purchaseReturnBookBuilder.partyType;
        transactionEntityDetailList=purchaseReturnBookBuilder.transactionEntityDetailList;
        debitNoteNumber =purchaseReturnBookBuilder.debitNoteNumber;
        itemisedPrices=purchaseReturnBookBuilder.itemisedPrices;
        totalPrice=purchaseReturnBookBuilder.totalPrice;
        remarks=purchaseReturnBookBuilder.remarks;
    }

    public LocalDateTime getDateOfCreditPurchaseReturn() {
        return dateOfCreditPurchaseReturn;
    }

    public List<TransactionEntityDetail> getTransactionEntityDetailList() {
        return transactionEntityDetailList;
    }

    public String getDebitNoteNumber() {
        return debitNoteNumber;
    }

    public void setLedgerFolioNumber(String ledgerFolioNumber) {
        this.ledgerFolioNumber = ledgerFolioNumber;
    }

    public String getLedgerFolioNumber() {
        return ledgerFolioNumber;
    }

    public Map<PriceQualifiers, Double> getItemisedPrices() {
        return itemisedPrices;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getRemarks() {
        return remarks;
    }

}
