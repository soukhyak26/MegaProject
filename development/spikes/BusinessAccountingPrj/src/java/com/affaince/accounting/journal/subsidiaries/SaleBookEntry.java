package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.*;
import com.affaince.accounting.transactions.TransactionEntityDetail;
import org.joda.time.LocalDateTime;

import java.util.*;


//ONly for credit purchase of saleable goods
public class SaleBookEntry {
    private final String merchantId;
    private final LocalDateTime dateOfCreditPurchase;
    private final String partyId;
    private final PartyTypes partyType;
    private final String accountId;
    private final AccountIdentifier accountIdentifier;
    private final List<TransactionEntityDetail> transactionEntityDetailList;
    private final String inwardInvoiceNumber;
    private String ledgerFolioNumber;
    private final double totalPrice;
    private final String remarks;

    public static class SaleBookEntryBuilder {
        private String merchantId;
        private LocalDateTime dateOfCreditPurchase;
        private String partyId;
        private PartyTypes partyType;
        private String accountId;
        private AccountIdentifier accountIdentifier;
        private List<TransactionEntityDetail> transactionEntityDetailList;
        private String outwardInvoiceNumber;
        private String ledgerFolioNumber;
        private double totalPrice;
        private String remarks;

        public SaleBookEntryBuilder(){
            this.transactionEntityDetailList= new ArrayList<>();
        }
        public SaleBookEntryBuilder merchantId(String merchantId){
            this.merchantId=merchantId;
            return this;
        }
        public SaleBookEntryBuilder partyId(String partyId){
            this.partyId=partyId;
            return this;
        }
        public SaleBookEntryBuilder partyType(PartyTypes partyType){
            this.partyType=partyType;
            return this;
        }
        public SaleBookEntryBuilder accountId(String accountId){
            this.accountId=accountId;
            return this;
        }
        public SaleBookEntryBuilder accountIdentifier(AccountIdentifier accountIdentifier){
            this.accountIdentifier=accountIdentifier;
            return this;
        }

        public SaleBookEntryBuilder dateOfCreditPurchase(LocalDateTime dateOfCreditPurchase){
            this.dateOfCreditPurchase=dateOfCreditPurchase;
            return this;
        }
        public SaleBookEntryBuilder transactionEntityDetailList(List<TransactionEntityDetail> transactionEntityDetailList){
            this.transactionEntityDetailList=transactionEntityDetailList;
            return this;
        }

        public SaleBookEntryBuilder inwardInvoiceNumber(String inwardInvoiceNumber){
            this.outwardInvoiceNumber =inwardInvoiceNumber;
            return this;
        }

        public SaleBookEntryBuilder totalPrice(double totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public SaleBookEntryBuilder remarks(String remarks){
            this.remarks=remarks;
            return this;
        }

        public SaleBookEntry build() {
            return SaleBookEntry.create(this);
        }
    }


    public static SaleBookEntryBuilder newBuilder(){
        return new SaleBookEntryBuilder();
    }
    public static SaleBookEntry create(SaleBookEntryBuilder saleBookEntryBuilder){
        return new SaleBookEntry(saleBookEntryBuilder);
    }
    public SaleBookEntry(SaleBookEntryBuilder saleBookEntryBuilder){
        merchantId= saleBookEntryBuilder.merchantId;
        dateOfCreditPurchase= saleBookEntryBuilder.dateOfCreditPurchase;
        partyId= saleBookEntryBuilder.partyId;
        partyType= saleBookEntryBuilder.partyType;
        accountId= saleBookEntryBuilder.accountId;
        accountIdentifier= saleBookEntryBuilder.accountIdentifier;
        transactionEntityDetailList= saleBookEntryBuilder.transactionEntityDetailList;
        inwardInvoiceNumber= saleBookEntryBuilder.outwardInvoiceNumber;
        totalPrice= saleBookEntryBuilder.totalPrice;
        remarks= saleBookEntryBuilder.remarks;
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


    public double getTotalPrice() {
        return totalPrice;
    }

    public String getRemarks() {
        return remarks;
    }
}
