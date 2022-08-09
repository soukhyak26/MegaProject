package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import com.affaince.accounting.journal.qualifiers.PriceQualifiers;
import com.affaince.accounting.transactions.TransactionEntityDetail;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReturnBookEntry {
    private final String merchantId;
    private LocalDateTime dateOfCreditSaleReturn;
    private final String partyId;
    private final PartyTypes partyType;
    private final String accountId;
    private final AccountIdentifier accountIdentifier;

    private List<TransactionEntityDetail> transactionEntityDetailList;
    private String creditNoteNumber;
    private String ledgerFolioNumber;
    private Map<PriceQualifiers,Double> itemisedPrices;
    private double totalPrice;
    private String remarks;

    public static class SalesReturnBookEntryBuilder {
        private String merchantId;
        private LocalDateTime dateOfCreditSaleReturn;
        private String partyId;
        private PartyTypes partyType;
        private String accountId;
        private AccountIdentifier accountIdentifier;

        private List<TransactionEntityDetail> transactionEntityDetailList;
        private String creditNoteNumber;
        private String ledgerFolioNumber;
        private Map<PriceQualifiers,Double> itemisedPrices;
        private double totalPrice;
        private String remarks;

        public SalesReturnBookEntryBuilder(){
            this.transactionEntityDetailList= new ArrayList<>();
            this.itemisedPrices= new HashMap<>();
        }
        public SalesReturnBookEntryBuilder merchantId(String merchantId){
            this.merchantId =merchantId;
            return this;
        }
        public SalesReturnBookEntryBuilder dateOfCreditSaleReturn(LocalDateTime dateOfCreditSaleReturn){
            this.dateOfCreditSaleReturn =dateOfCreditSaleReturn;
            return this;
        }
        public SalesReturnBookEntryBuilder partyId(String partyId){
            this.partyId=partyId;
            return this;
        }
        public SalesReturnBookEntryBuilder partyType(PartyTypes partyType){
            this.partyType=partyType;
            return this;
        }

        public SalesReturnBookEntryBuilder accountId(String accountId){
            this.accountId=accountId;
            return this;
        }

        public SalesReturnBookEntryBuilder accountIdentifier(AccountIdentifier accountIdentifier){
            this.accountIdentifier=accountIdentifier;
            return this;
        }

        public SalesReturnBookEntryBuilder transactionEntityDetailList(List<TransactionEntityDetail> transactionEntityDetailList){
            this.transactionEntityDetailList=transactionEntityDetailList;
            return this;
        }

        public SalesReturnBookEntryBuilder creditNoteNumber(String creditNoteNumber){
            this.creditNoteNumber =creditNoteNumber;
            return this;
        }

        public SalesReturnBookEntryBuilder itemisedPrices(Map<PriceQualifiers,Double> itemisedPrices){
            this.itemisedPrices=itemisedPrices;
            return this;
        }

        public SalesReturnBookEntryBuilder totalPrice(double totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public SalesReturnBookEntryBuilder remarks(String remarks){
            this.remarks=remarks;
            return this;
        }

        public SalesReturnBookEntry build() {
            return SalesReturnBookEntry.create(this);
        }
    }


    public static SalesReturnBookEntryBuilder newBuilder(){
        return new SalesReturnBookEntryBuilder();
    }
    public static SalesReturnBookEntry create(SalesReturnBookEntryBuilder salesReturnBookEntryBuilder){
        return new SalesReturnBookEntry(salesReturnBookEntryBuilder);
    }
    public SalesReturnBookEntry(SalesReturnBookEntryBuilder salesReturnBookEntryBuilder){
        merchantId= salesReturnBookEntryBuilder.merchantId;
        dateOfCreditSaleReturn = salesReturnBookEntryBuilder.dateOfCreditSaleReturn;
        partyId= salesReturnBookEntryBuilder.partyId;
        partyType= salesReturnBookEntryBuilder.partyType;
        accountId= salesReturnBookEntryBuilder.accountId;
        accountIdentifier= salesReturnBookEntryBuilder.accountIdentifier;
        transactionEntityDetailList= salesReturnBookEntryBuilder.transactionEntityDetailList;
        creditNoteNumber = salesReturnBookEntryBuilder.creditNoteNumber;
        itemisedPrices= salesReturnBookEntryBuilder.itemisedPrices;
        totalPrice= salesReturnBookEntryBuilder.totalPrice;
        remarks= salesReturnBookEntryBuilder.remarks;
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
