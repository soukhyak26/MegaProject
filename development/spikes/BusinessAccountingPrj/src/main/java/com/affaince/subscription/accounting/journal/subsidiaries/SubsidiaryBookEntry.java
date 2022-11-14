package com.affaince.subscription.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.*;
import com.affaince.subscription.accounting.transactions.TransactionEntityDetail;
import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.journal.qualifiers.PartyTypes;
import org.joda.time.LocalDateTime;

import java.util.*;


//ONly for credit purchase of saleable goods
public class SubsidiaryBookEntry {
    private final String merchantId;
    private final LocalDateTime dateOfTransaction;
    private final String partyId;
    private final PartyTypes partyType;
    private final String accountId;
    private final AccountIdentifier accountIdentifier;
    private final List<TransactionEntityDetail> transactionEntityDetailList;
    private final String invoiceNumber;
    private String ledgerFolioNumber;
    private final double totalPrice;
    private final String remarks;

    public static class SubsidiaryBookEntryBuilder {
        private String merchantId;
        private LocalDateTime dateOfTransaction;
        private String partyId;
        private PartyTypes partyType;
        private String accountId;
        private AccountIdentifier accountIdentifier;
        private List<TransactionEntityDetail> transactionEntityDetailList;
        private String invoiceNumber;
        private String ledgerFolioNumber;
        private double totalPrice;
        private String remarks;

        public SubsidiaryBookEntryBuilder(){
            this.transactionEntityDetailList= new ArrayList<>();
        }
        public SubsidiaryBookEntryBuilder merchantId(String merchantId){
            this.merchantId=merchantId;
            return this;
        }
        public SubsidiaryBookEntryBuilder partyId(String partyId){
            this.partyId=partyId;
            return this;
        }
        public SubsidiaryBookEntryBuilder partyType(PartyTypes partyType){
            this.partyType=partyType;
            return this;
        }
        public SubsidiaryBookEntryBuilder accountId(String accountId){
            this.accountId=accountId;
            return this;
        }
        public SubsidiaryBookEntryBuilder accountIdentifier(AccountIdentifier accountIdentifier){
            this.accountIdentifier=accountIdentifier;
            return this;
        }

        public SubsidiaryBookEntryBuilder dateOfTransaction(LocalDateTime dateOfCreditPurchase){
            this.dateOfTransaction =dateOfCreditPurchase;
            return this;
        }
        public SubsidiaryBookEntryBuilder transactionEntityDetailList(List<TransactionEntityDetail> transactionEntityDetailList){
            this.transactionEntityDetailList=transactionEntityDetailList;
            return this;
        }

        public SubsidiaryBookEntryBuilder invoiceNumber(String inwardInvoiceNumber){
            this.invoiceNumber =inwardInvoiceNumber;
            return this;
        }

        public SubsidiaryBookEntryBuilder totalPrice(double totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public SubsidiaryBookEntryBuilder remarks(String remarks){
            this.remarks=remarks;
            return this;
        }

        public SubsidiaryBookEntry build() {
            return SubsidiaryBookEntry.create(this);
        }
    }


    public static SubsidiaryBookEntryBuilder newBuilder(){
        return new SubsidiaryBookEntryBuilder();
    }
    public static SubsidiaryBookEntry create(SubsidiaryBookEntryBuilder subsidiaryBookEntryBuilder){
        return new SubsidiaryBookEntry(subsidiaryBookEntryBuilder);
    }
    public SubsidiaryBookEntry(SubsidiaryBookEntryBuilder subsidiaryBookEntryBuilder){
        merchantId= subsidiaryBookEntryBuilder.merchantId;
        dateOfTransaction = subsidiaryBookEntryBuilder.dateOfTransaction;
        partyId= subsidiaryBookEntryBuilder.partyId;
        partyType= subsidiaryBookEntryBuilder.partyType;
        accountId= subsidiaryBookEntryBuilder.accountId;
        accountIdentifier= subsidiaryBookEntryBuilder.accountIdentifier;
        transactionEntityDetailList= subsidiaryBookEntryBuilder.transactionEntityDetailList;
        invoiceNumber = subsidiaryBookEntryBuilder.invoiceNumber;
        totalPrice= subsidiaryBookEntryBuilder.totalPrice;
        remarks= subsidiaryBookEntryBuilder.remarks;
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

    public LocalDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }

    public List<TransactionEntityDetail> getTransactionEntityDetailList() {
        return transactionEntityDetailList;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
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

    @Override
    public String toString() {
        return "SubsidiaryBookEntry{" +
                "merchantId='" + merchantId + '\'' +
                ", dateOfTransaction=" + dateOfTransaction +
                ", partyId='" + partyId + '\'' +
                ", partyType=" + partyType +
                ", accountId='" + accountId + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", transactionEntityDetailList=" + transactionEntityDetailList +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", ledgerFolioNumber='" + ledgerFolioNumber + '\'' +
                ", totalPrice=" + totalPrice +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
