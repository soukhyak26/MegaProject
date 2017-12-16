package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.product.event.FixedExpenseChangedEvent;
import com.affaince.subscription.product.query.repository.FixedExpensePerProductViewRepository;
import com.affaince.subscription.product.query.view.FixedExpensePerProductView;
import com.affaince.subscription.product.vo.ProductwiseFixedExpenseId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 4/26/2017.
 */
@Component
public class FixedExpenseChangedEventListener {

    private FixedExpensePerProductViewRepository fixedExpensePerProductViewRepository;
    @Autowired
    public FixedExpenseChangedEventListener(FixedExpensePerProductViewRepository fixedExpensePerProductViewRepository){
        this.fixedExpensePerProductViewRepository=fixedExpensePerProductViewRepository;
    }
    @EventHandler
    public void on(FixedExpenseChangedEvent event){
        List<FixedExpensePerProductView> DescOrderedFixedExpenseVersions=fixedExpensePerProductViewRepository.findFirstByProductwiseFixedExpenseId_ProductIdOrderByEndDateDesc(event.getProductId());
        if(null != DescOrderedFixedExpenseVersions && DescOrderedFixedExpenseVersions.size()>0){
            FixedExpensePerProductView latestFixedExpensePerProductView=DescOrderedFixedExpenseVersions.get(0);
            latestFixedExpensePerProductView.setEndDate(event.getFromDate());
        }
        FixedExpensePerProductView fixedExpensePerProductView= new FixedExpensePerProductView(new ProductwiseFixedExpenseId(event.getProductId(),event.getFromDate()), event.getFixedExpensePerProduct().getFixedOperatingExpPerUnit());
        fixedExpensePerProductViewRepository.save(fixedExpensePerProductView);
    }
}
