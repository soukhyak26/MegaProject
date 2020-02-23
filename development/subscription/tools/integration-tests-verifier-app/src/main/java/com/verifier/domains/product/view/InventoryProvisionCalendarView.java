package com.verifier.domains.product.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.product.vo.InventoryProvisionCalendarVersionId;
import com.verifier.domains.product.vo.InventoryProvisionSegment;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="InventoryProvisionCalendarView")
public class InventoryProvisionCalendarView {
    @Id
    private InventoryProvisionCalendarVersionId inventoryProvisionCalendarVersionId;
    private long demand;
    private long provision;
    private ForecastContentStatus forecastContentStatus;

    public InventoryProvisionCalendarView(String productId, LocalDate startDate, LocalDate endDate) {
        this.inventoryProvisionCalendarVersionId =  new InventoryProvisionCalendarVersionId(productId,startDate,endDate);
        this.forecastContentStatus = ForecastContentStatus.ACTIVE;
    }

    public void addProvisionSegment(InventoryProvisionSegment matchingProvisionSegment, LocalDate startDate, LocalDate endDate){
        this.demand= matchingProvisionSegment.getDemand();
        this.provision=matchingProvisionSegment.getProvision();
    }

    public InventoryProvisionCalendarVersionId getInventoryProvisionCalendarVersionId() {
        return inventoryProvisionCalendarVersionId;
    }

    public void setInventoryProvisionCalendarVersionId(InventoryProvisionCalendarVersionId inventoryProvisionCalendarVersionId) {
        this.inventoryProvisionCalendarVersionId = inventoryProvisionCalendarVersionId;
    }

    public long getDemand() {
        return demand;
    }

    public void setDemand(long demand) {
        this.demand = demand;
    }

    public long getProvision() {
        return provision;
    }

    public void setProvision(long provision) {
        this.provision = provision;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
}
