package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.RevenueCreditedEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.business.query.repository.RevenueAccountViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountView;
import com.affaince.subscription.business.query.view.RevenueAccountView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class RevenueCreditEventListener {
    private final RevenueAccountViewRepository revenueAccountViewRepository;

    @Autowired
    public RevenueCreditEventListener(RevenueAccountViewRepository revenueAccountViewRepository) {
        this.revenueAccountViewRepository = revenueAccountViewRepository;
    }

    @EventHandler
    public void on(RevenueCreditedEvent event) {
        RevenueAccountView revenueAccountView = revenueAccountViewRepository.findOne(event.getYear());
        revenueAccountView.credit(event.getAmountToCredit());
        revenueAccountViewRepository.save(revenueAccountView);
    }
}
