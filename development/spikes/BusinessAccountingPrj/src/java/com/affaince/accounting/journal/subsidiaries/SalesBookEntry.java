package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.PartyTypes;
import com.affaince.accounting.journal.qualifiers.PriceQualifiers;
import com.affaince.accounting.transactions.TransactionEntityDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesBookEntry {
    private final String merchantId;
    private LocalDateTime dateOfCreditSale;
    private final String partyId;
    private final PartyTypes partyType;
    private List<TransactionEntityDetail> transactionEntityDetailList;
    private String outwardInvoiceNumber;
    private String ledgerFolioNumber;
    private Map<PriceQualifiers,Double> itemisedPrices;
    private double totalPrice;
    private String remarks;

    public static class SalesBookBuilder {
        private String merchantId;
        private LocalDateTime dateOfCreditSale;
        private String partyId;
        private PartyTypes partyType;
        private List<TransactionEntityDetail> transactionEntityDetailList;
        private String outwardInvoiceNumber;
        private String ledgerFolioNumber;
        private Map<PriceQualifiers,Double> itemisedPrices;
        private double totalPrice;
        private String remarks;

        public SalesBookBuilder(){
            this.transactionEntityDetailList= new ArrayList<>();
            this.itemisedPrices= new HashMap<>();
        }
        public SalesBookEntry.SalesBookBuilder merchantId(String merchantId){
            this.merchantId =merchantId;
            return this;
        }

        public SalesBookEntry.SalesBookBuilder dateOfCreditSale(LocalDateTime dateOfCreditSale){
            this.dateOfCreditSale =dateOfCreditSale;
            return this;
        }
        public SalesBookEntry.SalesBookBuilder partyId(String partyId){
            this.partyId=partyId;
            return this;
        }
        public SalesBookEntry.SalesBookBuilder partyType(PartyTypes partyType){
            this.partyType=partyType;
            return this;
        }

        public SalesBookEntry.SalesBookBuilder transactionEntityDetailList(List<TransactionEntityDetail> transactionEntityDetailList){
            this.transactionEntityDetailList=transactionEntityDetailList;
            return this;
        }

        public SalesBookEntry.SalesBookBuilder outwardInvoiceNumber(String outwardInvoiceNumber){
            this.outwardInvoiceNumber =outwardInvoiceNumber;
            return this;
        }

        public SalesBookEntry.SalesBookBuilder itemisedPrices(Map<PriceQualifiers,Double> itemisedPrices){
            this.itemisedPrices=itemisedPrices;
            return this;
        }

        public SalesBookEntry.SalesBookBuilder totalPrice(double totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public SalesBookEntry.SalesBookBuilder remarks(String remarks){
            this.remarks=remarks;
            return this;
        }

        public SalesBookEntry build() {
            return SalesBookEntry.create(this);
        }
    }


    public static SalesBookEntry.SalesBookBuilder newBuilder(){
        return new SalesBookEntry.SalesBookBuilder();
    }
    public static SalesBookEntry create(SalesBookEntry.SalesBookBuilder salesBookBuilder){
        return new SalesBookEntry(salesBookBuilder);
    }
    public SalesBookEntry(SalesBookEntry.SalesBookBuilder salesBookBuilder){
        merchantId=salesBookBuilder.merchantId;
        dateOfCreditSale =salesBookBuilder.dateOfCreditSale;
        partyId= salesBookBuilder.partyId;
        partyType=salesBookBuilder.partyType;
        transactionEntityDetailList=salesBookBuilder.transactionEntityDetailList;
        outwardInvoiceNumber =salesBookBuilder.outwardInvoiceNumber;
        itemisedPrices=salesBookBuilder.itemisedPrices;
        totalPrice=salesBookBuilder.totalPrice;
        remarks=salesBookBuilder.remarks;
    }

    public LocalDateTime getDateOfCreditSale() {
        return dateOfCreditSale;
    }

    public List<TransactionEntityDetail> getTransactionEntityDetailList() {
        return transactionEntityDetailList;
    }

    public String getOutwardInvoiceNumber() {
        return outwardInvoiceNumber;
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
