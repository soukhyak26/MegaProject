package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.*;
import com.affaince.accounting.transactions.TransactionEntityDetail;

import java.time.LocalDateTime;
import java.util.*;


//ONly for credit purchase of saleable goods
public class PurchaseBookEntry {
    private final String merchantId;
    private final LocalDateTime dateOfCreditPurchase;
    private final String partyId;
    private final PartyTypes partyType;
    private final List<TransactionEntityDetail> transactionEntityDetailList;
    private final String inwardInvoiceNumber;
    private String ledgerFolioNumber;
    private final Map<PriceQualifiers,Double> itemisedPrices;
    private final double totalPrice;
    private final String remarks;

    public static class PurchaseBookBuilder {
        private String merchantId;
        private LocalDateTime dateOfCreditPurchase;
        private String partyId;
        private PartyTypes partyType;
        private List<TransactionEntityDetail> transactionEntityDetailList;
        private String inwardInvoiceNumber;
        private String ledgerFolioNumber;
        private Map<PriceQualifiers,Double> itemisedPrices;
        private double totalPrice;
        private String remarks;

        public PurchaseBookBuilder(){
            this.transactionEntityDetailList= new ArrayList<>();
            this.itemisedPrices= new HashMap<>();
        }
        public PurchaseBookBuilder merchantId(String merchantId){
            this.merchantId=merchantId;
            return this;
        }
        public PurchaseBookBuilder partyId(String partyId){
            this.partyId=partyId;
            return this;
        }
        public PurchaseBookBuilder partyType(PartyTypes partyType){
            this.partyType=partyType;
            return this;
        }

        public PurchaseBookBuilder dateOfCreditPurchase(LocalDateTime dateOfCreditPurchase){
            this.dateOfCreditPurchase=dateOfCreditPurchase;
            return this;
        }
        public PurchaseBookBuilder transactionEntityDetailList(List<TransactionEntityDetail> transactionEntityDetailList){
            this.transactionEntityDetailList=transactionEntityDetailList;
            return this;
        }

        public PurchaseBookBuilder inwardInvoiceNumber(String inwardInvoiceNumber){
            this.inwardInvoiceNumber=inwardInvoiceNumber;
            return this;
        }

        public PurchaseBookBuilder itemisedPrices(Map<PriceQualifiers,Double> itemisedPrices){
            this.itemisedPrices=itemisedPrices;
            return this;
        }

        public PurchaseBookBuilder totalPrice(double totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public PurchaseBookBuilder remarks(String remarks){
            this.remarks=remarks;
            return this;
        }

        public PurchaseBookEntry build() {
            return PurchaseBookEntry.create(this);
        }
    }


    public static PurchaseBookEntry.PurchaseBookBuilder newBuilder(){
        return new PurchaseBookEntry.PurchaseBookBuilder();
    }
    public static PurchaseBookEntry create(PurchaseBookEntry.PurchaseBookBuilder purchaseBookBuilder){
        return new PurchaseBookEntry(purchaseBookBuilder);
    }
    public PurchaseBookEntry(PurchaseBookEntry.PurchaseBookBuilder purchaseBookBuilder){
        merchantId=purchaseBookBuilder.merchantId;
        dateOfCreditPurchase=purchaseBookBuilder.dateOfCreditPurchase;
        partyId=purchaseBookBuilder.partyId;
        partyType=purchaseBookBuilder.partyType;
        transactionEntityDetailList=purchaseBookBuilder.transactionEntityDetailList;
        inwardInvoiceNumber=purchaseBookBuilder.inwardInvoiceNumber;
        itemisedPrices=purchaseBookBuilder.itemisedPrices;
        totalPrice=purchaseBookBuilder.totalPrice;
        remarks=purchaseBookBuilder.remarks;
    }

    public LocalDateTime getDateOfCreditPurchase() {
        return dateOfCreditPurchase;
    }

    public List<TransactionEntityDetail> getTransactionEntityDetailList() {
        return transactionEntityDetailList;
    }

    public String getInwardInvoiceNumber() {
        return inwardInvoiceNumber;
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
