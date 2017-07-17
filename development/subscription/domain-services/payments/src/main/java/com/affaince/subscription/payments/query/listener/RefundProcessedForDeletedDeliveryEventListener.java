package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.RefundProcessedForDeletedDeliveriesEvent;
import com.affaince.subscription.payments.query.repository.RefundTransactionsViewRepository;
import com.affaince.subscription.payments.query.repository.RefundViewRepository;
import com.affaince.subscription.payments.query.view.RefundTransactionsView;
import com.affaince.subscription.payments.query.view.RefundView;
import com.affaince.subscription.payments.vo.RefundReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/17/2017.
 */
@Component
public class RefundProcessedForDeletedDeliveryEventListener {
    private final RefundViewRepository refundViewRepository;
    private final RefundTransactionsViewRepository refundTransactionsViewRepository;
    @Autowired
    public RefundProcessedForDeletedDeliveryEventListener(RefundViewRepository refundViewRepository, RefundTransactionsViewRepository refundTransactionsViewRepository) {
        this.refundViewRepository = refundViewRepository;
        this.refundTransactionsViewRepository = refundTransactionsViewRepository;
    }

    public void on(RefundProcessedForDeletedDeliveriesEvent event){
        if(event.getPaymentReceived()>0){
            RefundView refundView=refundViewRepository.findOne(event.getSubscriptionId());
            if(null== refundView){
                refundView= new RefundView(event.getSubscriptionId());
            }
            refundView.setRefundAmount(event.getPaymentReceived());
            refundViewRepository.save(refundView);
            RefundTransactionsView  refundTransactionsView= new RefundTransactionsView(event.getSubscriptionId());
            refundTransactionsView.setTotalRefundAmount(event.getPaymentReceived());
            refundTransactionsView.setRefundDate(event.getRefundProcessingDate());
            refundTransactionsView.setReasonForRefund(RefundReason.DELIVERIES_UPDATE);
            refundTransactionsViewRepository.save(refundTransactionsView);
        }
    }
}
