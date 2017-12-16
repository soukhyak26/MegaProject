package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.business.event.ProvisionForPurchaseCostRegisteredEvent;
import com.affaince.subscription.business.query.repository.PurchaseCostAccountTransactionsViewRepository;
import com.affaince.subscription.business.query.repository.PurchaseCostAccountViewRepository;
import com.affaince.subscription.business.query.view.PurchaseCostAccountTransactionsView;
import com.affaince.subscription.business.query.view.PurchaseCostAccountView;
import com.affaince.subscription.business.vo.TransactionReasonCode;
import com.affaince.subscription.business.vo.TransactionType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 22-01-2017.
 */
public class ProvisionForPurchaseCostRegisteredEventListener {
    private PurchaseCostAccountViewRepository purchaseCostAccountViewRepository;
    private PurchaseCostAccountTransactionsViewRepository purchaseCostAccountTransactionsViewRepository;

    @Autowired

    public ProvisionForPurchaseCostRegisteredEventListener(PurchaseCostAccountViewRepository purchaseCostAccountViewRepository, PurchaseCostAccountTransactionsViewRepository purchaseCostAccountTransactionsViewRepository) {
        this.purchaseCostAccountViewRepository = purchaseCostAccountViewRepository;
        this.purchaseCostAccountTransactionsViewRepository = purchaseCostAccountTransactionsViewRepository;
    }

    @EventHandler
    public void on(ProvisionForPurchaseCostRegisteredEvent event){
        PurchaseCostAccountView purchaseCostAccountView= new PurchaseCostAccountView(event.getId(),event.getProvisionForPurchaseOfGoods(),event.getCalendar(),event.getProvisionForPurchaseOfGoods(),event.getStartDate().toLocalDateTime(LocalTime.now()),event.getEndDate().toLocalDateTime(LocalTime.MIDNIGHT));
        purchaseCostAccountViewRepository.save(purchaseCostAccountView);
        PurchaseCostAccountTransactionsView purchaseCostAccountTransactionsView= new PurchaseCostAccountTransactionsView(event.getStartDate(),event.getProvisionForPurchaseOfGoods(), TransactionType.CREDIT, TransactionReasonCode.PURCHASE_COST_PROVISION_REGISTERED);
        purchaseCostAccountTransactionsViewRepository.save(purchaseCostAccountTransactionsView);

    }
}