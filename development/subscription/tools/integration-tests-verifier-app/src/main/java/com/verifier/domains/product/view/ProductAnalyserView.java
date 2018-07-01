package com.verifier.domains.product.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="ProductAnalyserView" )
public class ProductAnalyserView {
    @Id
    private String productAnalyserId;
    private long registeredProductCount;

    public ProductAnalyserView(String productAnalyserId, long registeredProductCount) {
        this.productAnalyserId = productAnalyserId;
        this.registeredProductCount = registeredProductCount;
    }

    public ProductAnalyserView(){}

    public String getProductAnalyserId() {
        return productAnalyserId;
    }

    public long getRegisteredProductCount() {
        return registeredProductCount;
    }

    public void setRegisteredProductCount(long registeredProductCount) {
        this.registeredProductCount = registeredProductCount;
    }
    public void addToRegisteredProductCount(long registeredProductCount){
        this.registeredProductCount += registeredProductCount;
    }

    public void reduceFromRegisteredProductCount(long registeredProductCount){
        this.registeredProductCount -= registeredProductCount;
    }

}
