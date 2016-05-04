package com.affaince.subscription.business.command.event;

import com.mongodb.DB;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by anayonkar on 29/4/16.
 */
public class CreateProvisionEvent {
    private String businessAccountId;
    private LocalDate provisionDate;
    private List<Double> provisionList;

    public CreateProvisionEvent() {

    }
    public CreateProvisionEvent(String businessAccountId,
                                List<Double> provisionList,
                                LocalDate provisionDate) {
        this.businessAccountId = businessAccountId;
        this.provisionList = provisionList;
        this.provisionDate = provisionDate;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public LocalDate getProvisionDate() {
        return provisionDate;
    }

    public List<Double> getProvisionList() {
        return provisionList;
    }
}
