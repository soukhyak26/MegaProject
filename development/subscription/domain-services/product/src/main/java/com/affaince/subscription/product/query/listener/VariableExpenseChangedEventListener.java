package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.VariableExpenseChangedEvent;
import com.affaince.subscription.product.query.repository.VariableExpensePerProductViewRepository;
import com.affaince.subscription.product.query.view.VariableExpensePerProductView;
import com.affaince.subscription.product.vo.ProductwiseVariableExpenseId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandar on 4/26/2017.
 */
public class VariableExpenseChangedEventListener {
    private VariableExpensePerProductViewRepository variableExpensePerProductViewRepository;

    @Autowired
    public VariableExpenseChangedEventListener(VariableExpensePerProductViewRepository variableExpensePerProductViewRepository){
        this.variableExpensePerProductViewRepository=variableExpensePerProductViewRepository;
    }

    @EventHandler
    public void on(VariableExpenseChangedEvent event){
        List<VariableExpensePerProductView> DescOrderedVariableExpenseVersions=variableExpensePerProductViewRepository.findFirstByProductwiseVariableExpenseId_ProductIdOrderByEndDateDesc(event.getProductId());
        if(null != DescOrderedVariableExpenseVersions && DescOrderedVariableExpenseVersions.size()>0){
            VariableExpensePerProductView latestVariableExpensePerProductView=DescOrderedVariableExpenseVersions.get(0);
            latestVariableExpensePerProductView.setEndDate(event.getFromDate());
        }
        VariableExpensePerProductView variableExpensePerProductView= new VariableExpensePerProductView(new ProductwiseVariableExpenseId(event.getProductId(),event.getFromDate()), event.getVariableExpensePerProduct().getVariableOperatingExpPerUnit());
        variableExpensePerProductViewRepository.save(variableExpensePerProductView);

    }
}
