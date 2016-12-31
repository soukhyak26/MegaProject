package com.affaince.subscription.product.query.view;

import com.affaince.subscription.product.vo.ProductwiseTaggedPriceVersionId;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "TaggedPriceVersions")
public class TaggedPriceVersionsView {
    @Id
    private ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId ;
    private double purchasePricePerUnit;
    private double MRP;
    private LocalDateTime taggedStartDate;
    private LocalDateTime taggedEndDate;

    public TaggedPriceVersionsView(String productId, String taggedPriceVersionId, double purchasePricePerUnit, double MRP, LocalDateTime taggedStartDate, LocalDateTime taggedEndDate) {
        this.productwiseTaggedPriceVersionId = new ProductwiseTaggedPriceVersionId(productId,taggedPriceVersionId);
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.taggedStartDate = taggedStartDate;
        this.taggedEndDate = taggedEndDate;
    }

    public ProductwiseTaggedPriceVersionId getProductwiseTaggedPriceVersionId() {
        return productwiseTaggedPriceVersionId;
    }

    public void setProductwiseTaggedPriceVersionId(ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId) {
        this.productwiseTaggedPriceVersionId = productwiseTaggedPriceVersionId;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public LocalDateTime getTaggedStartDate() {
        return taggedStartDate;
    }

    public void setTaggedStartDate(LocalDateTime taggedStartDate) {
        this.taggedStartDate = taggedStartDate;
    }

    public LocalDateTime getTaggedEndDate() {
        return taggedEndDate;
    }

    public void setTaggedEndDate(LocalDateTime taggedEndDate) {
        this.taggedEndDate = taggedEndDate;
    }
}
