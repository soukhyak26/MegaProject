package com.verifier.domains.product.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "TaggedPriceVersionsView")
public class TaggedPriceVersionsView {
    @Id
    private ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId ;
    private double purchasePricePerUnit;
    private double MRP;
    private double breakEvenPrice;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate taggedStartDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate taggedEndDate;

    public TaggedPriceVersionsView() {
    }

    public TaggedPriceVersionsView(ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId, double purchasePricePerUnit, double MRP, LocalDate taggedStartDate, LocalDate taggedEndDate) {
        this.productwiseTaggedPriceVersionId = productwiseTaggedPriceVersionId;
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

    public LocalDate getTaggedStartDate() {
        return taggedStartDate;
    }

    public void setTaggedStartDate(LocalDate taggedStartDate) {
        this.taggedStartDate = taggedStartDate;
    }

    public LocalDate getTaggedEndDate() {
        return taggedEndDate;
    }

    public void setTaggedEndDate(LocalDate taggedEndDate) {
        this.taggedEndDate = taggedEndDate;
    }

    public double getBreakEvenPrice() {
        return breakEvenPrice;
    }

    public void setBreakEvenPrice(double breakEvenPrice) {
        this.breakEvenPrice = breakEvenPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaggedPriceVersionsView that = (TaggedPriceVersionsView) o;
        return productwiseTaggedPriceVersionId.equals(that.productwiseTaggedPriceVersionId);
    }

    @Override
    public int hashCode() {
        return productwiseTaggedPriceVersionId.hashCode();
    }
}
