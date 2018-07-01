package com.verifier.domains.product.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="ProductInventoryView")
public class ProductInventoryView {
    @Id
    private String productInventoryId;
    private String productId;
    private long totalDemand;
    private long totalProvision;
    private long contingencyStock;

    public ProductInventoryView(){}
    public ProductInventoryView(String productInventoryId, String productId, long totalDemand, long totalProvision, long contingencyStock){
        this.productInventoryId=productInventoryId;
        this.productId = productId;
        this.totalDemand=totalDemand;
        this.totalProvision=totalProvision;
        this.contingencyStock=contingencyStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductInventoryId() {
        return productInventoryId;
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
