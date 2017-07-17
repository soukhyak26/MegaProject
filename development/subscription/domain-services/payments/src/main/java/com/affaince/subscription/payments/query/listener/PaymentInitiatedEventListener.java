package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.PaymentInitiatedEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostAccountViewRepository;
import com.affaince.subscription.payments.query.repository.PaymentTransactionViewRepository;
import com.affaince.subscription.payments.query.repository.TotalReceivableCostAccountViewRepository;
import com.affaince.subscription.payments.query.repository.TotalReceivedCostAccountViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostAccountView;
import com.affaince.subscription.payments.query.view.PaymentTransactionView;
import com.affaince.subscription.payments.query.view.TotalReceivableCostAccountView;
import com.affaince.subscription.payments.query.view.TotalReceivedCostAccountView;
import com.affaince.subscription.payments.vo.PaymentTransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PaymentInitiatedEventListener {
    private TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository;
    private TotalReceivedCostAccountViewRepository totalReceivedCostAccountViewRepository;
    private DeliveryCostAccountViewRepository deliveryCostAccountViewRepository;
    private PaymentTransactionViewRepository paymentTransactionViewRepository;

    @Autowired
    public PaymentInitiatedEventListener(TotalReceivableCostAccountViewRepository totalReceivableCostAccountViewRepository, TotalReceivedCostAccountViewRepository totalReceivedCostAccountViewRepository, DeliveryCostAccountViewRepository deliveryCostAccountViewRepository, PaymentTransactionViewRepository paymentTransactionViewRepository) {
        this.totalReceivableCostAccountViewRepository = totalReceivableCostAccountViewRepository;
        this.totalReceivedCostAccountViewRepository = totalReceivedCostAccountViewRepository;
        this.deliveryCostAccountViewRepository = deliveryCostAccountViewRepository;
        this.paymentTransactionViewRepository = paymentTransactionViewRepository;
    }

    @EventHandler
    public void on(PaymentInitiatedEvent event) {
        TotalReceivableCostAccountView totalReceivableCostAccountView=totalReceivableCostAccountViewRepository.findBySubscriptionId(event.getSubscriptionId()).get(0);
        TotalReceivedCostAccountView totalReceivedCostAccountView=totalReceivedCostAccountViewRepository.findBySubscriptionId(event.getSubscriptionId()).get(0);
        totalReceivableCostAccountView.debit(event.getPaidAmount());
        totalReceivableCostAccountViewRepository.save(totalReceivableCostAccountView);

        totalReceivedCostAccountView.credit(event.getPaidAmount());
        totalReceivedCostAccountViewRepository.save(totalReceivedCostAccountView);

        PaymentTransactionView paymentTransactionView = new PaymentTransactionView(event.getPaymentDate(),event.getSubscriptionId(),event.getPaidAmount(), PaymentTransactionType.PAYMENT_BY_MONEY);
        paymentTransactionViewRepository.save(paymentTransactionView);

        List<DeliveryCostAccountView> deliveriesForASubscription= deliveryCostAccountViewRepository.findByDeliveryId_SubscriptionId(event.getSubscriptionId());
        Map<String,Double> paymentToBeAdjustedAgainstDeliveries= event.getPaymentToBeAdjustedAgainstDeliveries();
        for(DeliveryCostAccountView deliveryCostAccountView : deliveriesForASubscription){
            Double paymentToBeAdjustedAgainstDelivery=paymentToBeAdjustedAgainstDeliveries.get(deliveryCostAccountView.getDeliveryId().getDeliveryId());
            if(null != paymentToBeAdjustedAgainstDelivery && paymentToBeAdjustedAgainstDelivery.doubleValue()>0) {
                deliveryCostAccountView.creditToPaymentReceived(paymentToBeAdjustedAgainstDelivery);
                deliveryCostAccountViewRepository.save(deliveryCostAccountView);
            }
        }

    }
}
