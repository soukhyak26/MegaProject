package com.affaince.subscription.business.command;

import com.affaince.subscription.business.provision.ProvisionIndex;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by anayonkar on 29/4/16.
 */
public class CreateProvisionCommand {
    private LocalDate provisionDate;
    private String businessAccountId;
    private List<Double> provisionList;
    public CreateProvisionCommand(List<Double> provisionList, LocalDate provisionDate) {
        this.provisionList = provisionList;
        this.provisionDate = provisionDate;
        businessAccountId = Integer.valueOf(provisionDate.getYear()).toString();
    }

    public LocalDate getProvisionDate() {
        return provisionDate;
    }

    public List<Double> getProvisionList() {
        return provisionList;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateProvisionCommand{");
        sb.append("provisionDate=").append(provisionDate);
        sb.append(", businessAccountId='").append(businessAccountId).append('\'');
        sb.append(", provisionList=").append(provisionList);
        sb.append('}');
        return sb.toString();
    }

    /*private String getProvisionListAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(ProvisionIndex provisionIndex : ProvisionIndex.values()) {
            switch (provisionIndex) {
                case MAX_CAPACITY:
                    break;
                default:
                    sb.append(" ").append(provisionIndex.name()).append("=").append(provisionList.get(provisionIndex.getIndex())).append(",");
            }
        }
        if(sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }*/
}
