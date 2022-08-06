package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.PartyTypes;
import com.affaince.accounting.journal.qualifiers.PriceQualifiers;
import com.affaince.accounting.transactions.TransactionEntityDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReturnBookEntry {
    private final String merchantId;
    private LocalDateTime dateOfCreditSaleReturn;
    private final String partyId;
    private final PartyTypes partyType;
    private List<TransactionEntityDetail> transactionEntityDetailList;
    private String creditNoteNumber;
    private String ledgerFolioNumber;
    private Map<PriceQualifiers,Double> itemisedPrices;
    private double totalPrice;
    private String remarks;

    public static class SalesReturnBookBuilder {
        private String merchantId;
        private LocalDateTime dateOfCreditSaleReturn;
        private String partyId;
        private PartyTypes partyType;
        private List<TransactionEntityDetail> transactionEntityDetailList;
        private String creditNoteNumber;
        private String ledgerFolioNumber;
        private Map<PriceQualifiers,Double> itemisedPrices;
        private double totalPrice;
        private String remarks;

        public SalesReturnBookBuilder(){
            this.transactionEntityDetailList= new ArrayList<>();
            this.itemisedPrices= new HashMap<>();
        }
        public SalesReturnBookEntry.SalesReturnBookBuilder merchantId(String merchantId){
            this.merchantId =merchantId;
            return this;
        }
        public SalesReturnBookEntry.SalesReturnBookBuilder dateOfCreditSaleReturn(LocalDateTime dateOfCreditSaleReturn){
            this.dateOfCreditSaleReturn =dateOfCreditSaleReturn;
            return this;
        }
        public SalesReturnBookEntry.SalesReturnBookBuilder partyId(String partyId){
            this.partyId=partyId;
            return this;
        }
        public SalesReturnBookEntry.SalesReturnBookBuilder partyType(PartyTypes partyType){
            this.partyType=partyType;
            return this;
        }

        public SalesReturnBookEntry.SalesReturnBookBuilder transactionEntityDetailList(List<TransactionEntityDetail> transactionEntityDetailList){
            this.transactionEntityDetailList=transactionEntityDetailList;
            return this;
        }

        public SalesReturnBookEntry.SalesReturnBookBuilder creditNoteNumber(String creditNoteNumber){
            this.creditNoteNumber =creditNoteNumber;
            return this;
        }

        public SalesReturnBookEntry.SalesReturnBookBuilder itemisedPrices(Map<PriceQualifiers,Double> itemisedPrices){
            this.itemisedPrices=itemisedPrices;
            return this;
        }

        public SalesReturnBookEntry.SalesReturnBookBuilder totalPrice(double totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public SalesReturnBookEntry.SalesReturnBookBuilder remarks(String remarks){
            this.remarks=remarks;
            return this;
        }

        public SalesReturnBookEntry build() {
            return SalesReturnBookEntry.create(this);
        }
    }


    public static SalesReturnBookEntry.SalesReturnBookBuilder newBuilder(){
        return new SalesReturnBookEntry.SalesReturnBookBuilder();
    }
    public static SalesReturnBookEntry create(SalesReturnBookEntry.SalesReturnBookBuilder salesReturnBookBuilder){
        return new SalesReturnBookEntry(salesReturnBookBuilder);
    }
    public SalesReturnBookEntry(SalesReturnBookEntry.SalesReturnBookBuilder salesReturnBookBuilder){
        merchantId=salesReturnBookBuilder.merchantId;
        dateOfCreditSaleReturn =salesReturnBookBuilder.dateOfCreditSaleReturn;
        partyId=salesReturnBookBuilder.partyId;
        partyType=salesReturnBookBuilder.partyType;
        transactionEntityDetailList=salesReturnBookBuilder.transactionEntityDetailList;
        creditNoteNumber =salesReturnBookBuilder.creditNoteNumber;
        itemisedPrices=salesReturnBookBuilder.itemisedPrices;
        totalPrice=salesReturnBookBuilder.totalPrice;
        remarks=salesReturnBookBuilder.remarks;
    }

    public LocalDateTime getDateOfCreditSaleReturn() {
        return dateOfCreditSaleReturn;
    }

    public List<TransactionEntityDetail> getTransactionEntityDetailList() {
        return transactionEntityDetailList;
    }

    public String getCreditNoteNumber() {
        return creditNoteNumber;
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
