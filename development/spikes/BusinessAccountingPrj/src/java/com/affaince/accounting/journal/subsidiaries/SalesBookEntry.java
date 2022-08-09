package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
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
    private final String accountId;
    private final AccountIdentifier accountIdentifier;
    private List<TransactionEntityDetail> transactionEntityDetailList;
    private String outwardInvoiceNumber;
    private String ledgerFolioNumber;
    private Map<PriceQualifiers,Double> itemisedPrices;
    private double totalPrice;
    private String remarks;

    public static class SalesBookEntryBuilder {
        private String merchantId;
        private LocalDateTime dateOfCreditSale;
        private String partyId;
        private PartyTypes partyType;
        private String accountId;
        private AccountIdentifier accountIdentifier;

        private List<TransactionEntityDetail> transactionEntityDetailList;
        private String outwardInvoiceNumber;
        private String ledgerFolioNumber;
        private Map<PriceQualifiers,Double> itemisedPrices;
        private double totalPrice;
        private String remarks;

        public SalesBookEntryBuilder(){
            this.transactionEntityDetailList= new ArrayList<>();
            this.itemisedPrices= new HashMap<>();
        }
        public SalesBookEntryBuilder merchantId(String merchantId){
            this.merchantId =merchantId;
            return this;
        }

        public SalesBookEntryBuilder dateOfCreditSale(LocalDateTime dateOfCreditSale){
            this.dateOfCreditSale =dateOfCreditSale;
            return this;
        }
        public SalesBookEntryBuilder partyId(String partyId){
            this.partyId=partyId;
            return this;
        }
        public SalesBookEntryBuilder partyType(PartyTypes partyType){
            this.partyType=partyType;
            return this;
        }

        public SalesBookEntryBuilder accountId(String accountId){
            this.accountId=accountId;
            return this;
        }

        public SalesBookEntryBuilder accountIdentifier(AccountIdentifier accountIdentifier){
            this.accountIdentifier=accountIdentifier;
            return this;
        }

        public SalesBookEntryBuilder transactionEntityDetailList(List<TransactionEntityDetail> transactionEntityDetailList){
            this.transactionEntityDetailList=transactionEntityDetailList;
            return this;
        }

        public SalesBookEntryBuilder outwardInvoiceNumber(String outwardInvoiceNumber){
            this.outwardInvoiceNumber =outwardInvoiceNumber;
            return this;
        }

        public SalesBookEntryBuilder itemisedPrices(Map<PriceQualifiers,Double> itemisedPrices){
            this.itemisedPrices=itemisedPrices;
            return this;
        }

        public SalesBookEntryBuilder totalPrice(double totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public SalesBookEntryBuilder remarks(String remarks){
            this.remarks=remarks;
            return this;
        }

        public SalesBookEntry build() {
            return SalesBookEntry.create(this);
        }
    }


    public static SalesBookEntryBuilder newBuilder(){
        return new SalesBookEntryBuilder();
    }
    public static SalesBookEntry create(SalesBookEntryBuilder salesBookEntryBuilder){
        return new SalesBookEntry(salesBookEntryBuilder);
    }
    public SalesBookEntry(SalesBookEntryBuilder salesBookEntryBuilder){
        merchantId= salesBookEntryBuilder.merchantId;
        dateOfCreditSale = salesBookEntryBuilder.dateOfCreditSale;
        partyId= salesBookEntryBuilder.partyId;
        partyType= salesBookEntryBuilder.partyType;
        accountId= salesBookEntryBuilder.accountId;
        accountIdentifier= salesBookEntryBuilder.accountIdentifier;
        transactionEntityDetailList= salesBookEntryBuilder.transactionEntityDetailList;
        outwardInvoiceNumber = salesBookEntryBuilder.outwardInvoiceNumber;
        itemisedPrices= salesBookEntryBuilder.itemisedPrices;
        totalPrice= salesBookEntryBuilder.totalPrice;
        remarks= salesBookEntryBuilder.remarks;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getPartyId() {
        return partyId;
    }

    public PartyTypes getPartyType() {
        return partyType;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
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
