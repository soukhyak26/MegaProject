package com.affaince.accounting.journal.processor.factory;

import com.affaince.accounting.journal.entity.SourceDocument;
import com.affaince.accounting.journal.processor.*;
import com.affaince.accounting.journal.processor.contract.AccountIdentificationRulesProcessor;
import com.affaince.accounting.journal.qualifiers.TransactionEvents;

public class AccountIdentificationRulesProcessorFactory {

    public static AccountIdentificationRulesProcessor getAccountIdentificationRulesProcessor(SourceDocument sourceDocument){
        TransactionEvents transactionEvent = sourceDocument.getTransactionEvent();
        switch (transactionEvent) {
            case CAPITAL_INVESTMENT:
                return new CapitalInvestmentEventProcessor();
            case SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER:
                return new DistributionServicesAvailedEventProcessor();
            case GOODS_PURCHASE_BY_BUSINESS:
                return new GoodsPurchaseEventProcessor();
            case GOODS_DELIVERY_TO_SUBSCRIBER:
                return new GoodsDeliveryToSubscriberEventProcessor();
            case PAYMENT_MADE_TO_SERVICE_PROVIDER:
                return new DistributionServicesPaymentEventProcessor();
            case PAYMENT_MADE_TO_SUPPLIER:
                return new GoodsPaymentToSupplierEventProcessor();
            case PAYMENT_RECEIVED_FROM_SUBSCRIBER:
                return new PaymentReceiptFromSubscriberEventProcessor();
            case TAX_PAYMENT:
                return new TaxPaymentEventProcessor();
            case PAYMENT_OF_RENT:
                return new PremiseRentPaymentEventProcessor();

        }
        return null;
    }
}
