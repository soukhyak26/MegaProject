package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.CostOfDeliveredGoodsDebitedEvent;
import com.affaince.subscription.business.query.repository.PurchaseCostAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.PurchaseCostAccountViewRepository;
import com.affaince.subscription.business.query.view.PurchaseCostAccountTransactionsView;
import com.affaince.subscription.business.query.view.PurchaseCostAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 19-02-2017.
 */
@Component
public class CostOfDeliveredGoodsDebitedEventListener {
    private final PurchaseCostAccountViewRepository purchaseCostAccountViewRepository;
    private final PurchaseCostAccountTransactionsViewRepository purchaseCostAccountTransactionsViewRepository;

    @Autowired
    public CostOfDeliveredGoodsDebitedEventListener(PurchaseCostAccountViewRepository purchaseCostAccountViewRepository, PurchaseCostAccountTransactionsViewRepository purchaseCostAccountTransactionsViewRepository) {
        this.purchaseCostAccountViewRepository = purchaseCostAccountViewRepository;
        this.purchaseCostAccountTransactionsViewRepository = purchaseCostAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on(CostOfDeliveredGoodsDebitedEvent event){
        //PurchaseCostAccountView purchaseCostAccountView = new PurchaseCostAccountView(event.getBusinessAccountId(),event.getAmountToBeDebited()+event.getRevisedProvisionAmount(),event.getRevisedProvisionAmount(),event.getStartDate().toLocalDateTime(LocalTime.now()),event.getEndDate().toLocalDateTime(LocalTime.MIDNIGHT));
        PurchaseCostAccountView purchaseCostAccountView=purchaseCostAccountViewRepository.findOne(event.getBusinessAccountId());
        purchaseCostAccountView.debit(event.getAmountToBeDebited(),event.getStartDate());
        purchaseCostAccountViewRepository.save(purchaseCostAccountView);

        PurchaseCostAccountTransactionsView purchaseCostAccountTransactionsView= new PurchaseCostAccountTransactionsView(event.getStartDate(),event.getAmountToBeDebited(), TransactionType.DEBIT, TransactionReasonCode.PURCHASE_COST_PROVISION_DEBITED);
        purchaseCostAccountTransactionsViewRepository.save(purchaseCostAccountTransactionsView);
    }
}
