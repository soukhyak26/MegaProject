package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.BusinessAccountConfigurationSetEvent;
import com.affaince.subscription.business.query.repository.BusinessAccountConfigurationViewRepository;
import com.affaince.subscription.business.query.view.BusinessAccountConfigurationView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 10/8/2017.
 */
@Component
public class BusinessAccountConfigurationSetEventListener {
    private final BusinessAccountConfigurationViewRepository businessAccountConfigurationViewRepository;
    @Autowired
    public BusinessAccountConfigurationSetEventListener(BusinessAccountConfigurationViewRepository businessAccountConfigurationViewRepository) {
        this.businessAccountConfigurationViewRepository = businessAccountConfigurationViewRepository;
    }

    @EventHandler
    public void on(BusinessAccountConfigurationSetEvent event){
        Integer businessAccountId= event.getId();
        BusinessAccountConfigurationView businessAccountConfigurationView= businessAccountConfigurationViewRepository.findOne(businessAccountId);
        if(null== businessAccountConfigurationView){
            businessAccountConfigurationView= new BusinessAccountConfigurationView(event.getId(),event.getBudgetAdjustmentOptions());
        }else{
            businessAccountConfigurationView.setBudgetAdjustmentOptions(event.getBudgetAdjustmentOptions());
        }
        businessAccountConfigurationViewRepository.save(businessAccountConfigurationView);
    }
}
