package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.RefundProcessedForUndeliveredItemsEvent;
import com.affaince.subscription.payments.query.repository.RefundTransactionsViewRepository;
import com.affaince.subscription.payments.query.view.RefundTransactionsView;
import com.affaince.subscription.payments.vo.RefundReason;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/13/2017.
 */
@Component
public class RefundProcessedForUndeliveredItemsEventListener {
    private final RefundTransactionsViewRepository refundTransactionsViewRepository;
    @Autowired
    public RefundProcessedForUndeliveredItemsEventListener(RefundTransactionsViewRepository refundTransactionsViewRepository) {
        this.refundTransactionsViewRepository = refundTransactionsViewRepository;
    }

    @EventHandler
    public void on(RefundProcessedForUndeliveredItemsEvent event){
        RefundTransactionsView refundTransactionsView= new RefundTransactionsView(event.getSubscriptionId());
        refundTransactionsView.setDeliveryId(event.getDeliveryId());
        refundTransactionsView.setItemWiseRefundAmountDetails(event.getItemWiseRefundAmountDetails());
        refundTransactionsView.setReasonForRefund(RefundReason.DELIVERY_FAILURE);
        refundTransactionsView.setRefundDate(event.getRefundDate());
        refundTransactionsView.setTotalRefundAmount(event.getTotalRefundableAmount());
        refundTransactionsViewRepository.save(refundTransactionsView);
    }
}
