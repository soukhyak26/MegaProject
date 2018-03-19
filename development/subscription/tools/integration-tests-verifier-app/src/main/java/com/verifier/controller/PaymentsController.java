package com.verifier.controller;

import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.affaince.subscription.date.SysDate;
import com.verifier.domains.payments.repository.*;
import com.verifier.domains.payments.view.*;
import com.verifier.domains.product.view.TaggedPriceVersionsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping(value = "payments")
public class PaymentsController {
    private final DeliveryCostAccountViewRepository deliveryCostAccountViewRepository;
    private final DeliveryDetailsViewRepository deliveryDetailsViewRepository;
    private final PaymentAccountViewRepository paymentAccountViewRepository;
    private final PaymentInstallmentViewRepository paymentInstallmentViewRepository;
    private final PaymentSchemesViewRepository paymentSchemesViewRepository;
    private final PaymentTransactionViewRepository paymentTransactionViewRepository;
    private final ProductOfferPricesViewRepository productOfferPricesViewRepository;
    private final ProductTaggedPricesViewRepository productTaggedPricesViewRepository;
    private final ProductViewRepository productViewRepository;
    private final RefundTransactionsViewRepository refundTransactionsViewRepository;
    private final RefundViewRepository refundViewRepository;
    private final TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository;
    private final TotalReceivedCostAccountViewRepository totalReceivedCostAccountViewRepository;
    private final TotalSubscriptionCostAccountViewRepository totalSubscriptionCostAccountViewRepository;

    @Autowired
    public PaymentsController(DeliveryCostAccountViewRepository deliveryCostAccountViewRepository, DeliveryDetailsViewRepository deliveryDetailsViewRepository, PaymentAccountViewRepository paymentAccountViewRepository, PaymentInstallmentViewRepository paymentInstallmentViewRepository, PaymentSchemesViewRepository paymentSchemesViewRepository, PaymentTransactionViewRepository paymentTransactionViewRepository, ProductOfferPricesViewRepository productOfferPricesViewRepository, ProductTaggedPricesViewRepository productTaggedPricesViewRepository, ProductViewRepository productViewRepository, RefundTransactionsViewRepository refundTransactionsViewRepository, RefundViewRepository refundViewRepository, TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository, TotalReceivedCostAccountViewRepository totalReceivedCostAccountViewRepository, TotalSubscriptionCostAccountViewRepository totalSubscriptionCostAccountViewRepository) {
        this.deliveryCostAccountViewRepository = deliveryCostAccountViewRepository;
        this.deliveryDetailsViewRepository = deliveryDetailsViewRepository;
        this.paymentAccountViewRepository = paymentAccountViewRepository;
        this.paymentInstallmentViewRepository = paymentInstallmentViewRepository;
        this.paymentSchemesViewRepository = paymentSchemesViewRepository;
        this.paymentTransactionViewRepository = paymentTransactionViewRepository;
        this.productOfferPricesViewRepository = productOfferPricesViewRepository;
        this.productTaggedPricesViewRepository = productTaggedPricesViewRepository;
        this.productViewRepository = productViewRepository;
        this.refundTransactionsViewRepository = refundTransactionsViewRepository;
        this.refundViewRepository = refundViewRepository;
        this.totalReceivableCostAccountViewRepository = totalReceivableCostAccountViewRepository;
        this.totalReceivedCostAccountViewRepository = totalReceivedCostAccountViewRepository;
        this.totalSubscriptionCostAccountViewRepository = totalSubscriptionCostAccountViewRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliverycostaccount/{deliveryId}/{subscriberId}/{subscriptionId}")
    public ResponseEntity<DeliveryCostAccountView> getDeliveryCostAccount(@PathVariable String deliveryId, @PathVariable String subscriberId, @PathVariable String subscriptionId) {
        DeliveryCostAccountView deliveryCostAccountView = deliveryCostAccountViewRepository.findOne(new DeliveryId(deliveryId, subscriberId, subscriptionId));
        return new ResponseEntity<>(deliveryCostAccountView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "deliverydetails/{deliveryId}/{subscriberId}/{subscriptionId}")
    public ResponseEntity<DeliveryDetailsView> getDeliveryDetails(@PathVariable String deliveryId, @PathVariable String subscriberId, @PathVariable String subscriptionId) {
        DeliveryDetailsView deliveryDetailsView = deliveryDetailsViewRepository.findOne(new DeliveryId(deliveryId, subscriberId, subscriptionId));
        return new ResponseEntity<>(deliveryDetailsView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "paymentaccount/{subscriptionId}")
    public ResponseEntity<PaymentAccountView> getPaymentAccount(@PathVariable String subscriptionId) {
        PaymentAccountView paymentAccountView = paymentAccountViewRepository.findOne(subscriptionId);
        return new ResponseEntity<>(paymentAccountView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "paymentinstallments/{subscriptionId}")
    public ResponseEntity<PaymentInstallmentView> getPaymentInstallments(@PathVariable String subscriptionId) {
        PaymentInstallmentView paymentInstallmentView = paymentInstallmentViewRepository.findOne(subscriptionId);
        return new ResponseEntity<>(paymentInstallmentView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "paymentschemes/{schemeId}")
    public ResponseEntity<PaymentSchemesView> getPaymentSchemes(@PathVariable String schemeId) {
        PaymentSchemesView paymentSchemesView = paymentSchemesViewRepository.findOne(schemeId);
        return new ResponseEntity<>(paymentSchemesView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "paymentschemes")
    public ResponseEntity<List<PaymentSchemesView>> getAllPaymentSchemes() {
        List<PaymentSchemesView> paymentSchemesViews = paymentSchemesViewRepository.findAll();
        return new ResponseEntity<>(paymentSchemesViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "paymentschemes/active")
    public ResponseEntity<List<PaymentSchemesView>> getAllActivePaymentSchemes() {
        List<PaymentSchemesView> paymentSchemesViews = paymentSchemesViewRepository.findBySchemeEndDateBefore(SysDate.now());
        return new ResponseEntity<>(paymentSchemesViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "transactions/{subscriptionId}")
    public ResponseEntity<List<PaymentTransactionView>> getPaymentTransactions(@PathVariable String subscriptionId) {
        List<PaymentTransactionView> transactionViews = paymentTransactionViewRepository.findBySubscriptionId(subscriptionId);
        return new ResponseEntity<>(transactionViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "offerprice/{productId}/{priceBucketId}")
    public ResponseEntity<ProductOfferPricesView> getProductOfferPrice(@PathVariable String productId, @PathVariable String priceBucketId) {
        ProductOfferPricesView offerPricesView = productOfferPricesViewRepository.findOne(new ProductwisePriceBucketId(productId, priceBucketId));
        return new ResponseEntity<ProductOfferPricesView>(offerPricesView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "offerprice/{productId}")
    public ResponseEntity<List<ProductOfferPricesView>> getProductOfferPrices(@PathVariable String productId) {
        List<ProductOfferPricesView> offerPricesViews = productOfferPricesViewRepository.findByProductwisePriceBucketId_ProductId(productId);
        return new ResponseEntity<>(offerPricesViews, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "offerprice/{productId}")
    public ResponseEntity<List<ProductTaggedPricesView>> getProductTaggedPrice(@PathVariable String productId) {
        List<ProductTaggedPricesView> taggedPricesForAProdct = productTaggedPricesViewRepository.findByProductwiseTaggedPriceVersionId_ProductId(productId);
        return new ResponseEntity<>(taggedPricesForAProdct, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "product/{productId}")
    public ResponseEntity<ProductView> getProduct(@PathVariable String productId) {
        ProductView productView = productViewRepository.findOne(productId);
        return new ResponseEntity<>(productView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "refundtransactions/{paymentAccountId}")
    public ResponseEntity<RefundTransactionsView> getRefundTransactions(@PathVariable String paymentAccountId) {
        RefundTransactionsView refundTransactionsView = refundTransactionsViewRepository.findOne(paymentAccountId);
        return new ResponseEntity<>(refundTransactionsView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "totalreceivables/{paymentAccountId}")
    public ResponseEntity<TotalReceivableCostAccountView> getTotalReceivables(@PathVariable String paymentAccountId) {
        TotalReceivableCostAccountView totalReceivableCostAccountView = totalReceivableCostAccountViewRepository.findOne(paymentAccountId);
        return new ResponseEntity<>(totalReceivableCostAccountView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "totalreceived/{paymentAccountId}")
    public ResponseEntity<TotalReceivedCostAccountView> getTotalReceived(@PathVariable String paymentAccountId) {
        TotalReceivedCostAccountView totalReceivedCostAccountView = totalReceivedCostAccountViewRepository.findOne(paymentAccountId);
        return new ResponseEntity<>(totalReceivedCostAccountView, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "totalsubscription/{paymentAccountId}")
    public ResponseEntity<TotalSubscriptionCostAccountView> getTotalSubscriptionCost(@PathVariable String paymentAccountId) {
        TotalSubscriptionCostAccountView totalSubscriptionCostAccountView = totalSubscriptionCostAccountViewRepository.findOne(paymentAccountId);
        return new ResponseEntity<>(totalSubscriptionCostAccountView, HttpStatus.OK);
    }

}
