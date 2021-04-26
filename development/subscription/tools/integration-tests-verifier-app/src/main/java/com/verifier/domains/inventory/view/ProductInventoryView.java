package com.verifier.domains.inventory.view;

import com.verifier.domains.inventory.vo.InventoryVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="ProductInventoryView")
public class ProductInventoryView {
    @Id
    InventoryVersionId inventoryVersionId;
    // the value of demand will be according to futureDemandPeriod setting in product configuration
    private long totalDemand;
    private LocalDate demandStartDate;
    private LocalDate demandEndDate;
    private boolean isActiveDemand;
    private long totalProvision;
    private long contingencyStock;

    public ProductInventoryView(){}
    public ProductInventoryView(String productInventoryId,String productId,long totalDemand,long totalProvision,long contingencyStock,LocalDate demandChangeDate){
        this.inventoryVersionId=new InventoryVersionId(productInventoryId,productId,demandChangeDate);
        this.totalDemand=totalDemand;
        this.totalProvision=totalProvision;
        this.contingencyStock=contingencyStock;
        this.demandStartDate = demandChangeDate;
        this.demandEndDate = demandChangeDate;
        this.isActiveDemand = false;
    }

    public ProductInventoryView(InventoryVersionId inventoryVersionId,long totalDemand,long totalProvision,long contingencyStock,LocalDate demandStartDate, LocalDate demandEndDate){
        this.inventoryVersionId=inventoryVersionId;
        this.totalDemand=totalDemand;
        this.totalProvision=totalProvision;
        this.contingencyStock=contingencyStock;
        this.demandStartDate = demandStartDate;
        this.demandEndDate = demandEndDate;
        this.isActiveDemand = true;
    }

    public InventoryVersionId getInventoryVersionId() {
        return inventoryVersionId;
    }

    public LocalDate getDemandStartDate() {
        return demandStartDate;
    }

    public LocalDate getDemandEndDate() {
        return demandEndDate;
    }

    public boolean isActiveDemand() {
        return isActiveDemand;
    }

    public void setDemandStartDate(LocalDate demandStartDate) {
        this.demandStartDate = demandStartDate;
    }

    public void setDemandEndDate(LocalDate demandEndDate) {
        this.demandEndDate = demandEndDate;
    }

    public void setActiveDemand(boolean activeDemand) {
        isActiveDemand = activeDemand;
    }

    public long getTotalDemand() {
        return totalDemand;
    }

    public void setTotalDemand(long totalDemand) {
        this.totalDemand = totalDemand;
    }

    public long getTotalProvision() {
        return totalProvision;
    }

    public void setTotalProvision(long totalProvision) {
        this.totalProvision = totalProvision;
    }

    public long getContingencyStock() {
        return contingencyStock;
    }

    public void setContingencyStock(long contingencyStock) {
        this.contingencyStock = contingencyStock;
    }

    public void addToDemand(long incrementalDemand){
        this.totalDemand +=incrementalDemand;
    }

    public void addToProvision(long incrementalProvision){
        this.totalProvision += incrementalProvision;
    }

    public void deductFromDemand(long decreasedDemand){
        this.totalDemand -= decreasedDemand;
    }
    public void deductFromProvision(long decreasedProvision){
        this.totalProvision -= decreasedProvision;
    }
}
