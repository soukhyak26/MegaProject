package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/26/2017.
 */
@Document(collection="ProductTaggedPricesView")
public class ProductTaggedPricesView {
    private ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId;
    private double purchasePricePerUnit;
    private double MRP;
    private LocalDate taggedPriceStartDate;
    private LocalDate taggedPriceEndaDate;


    public ProductTaggedPricesView(ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId, double purchasePricePerUnit, double MRP,LocalDate taggedPriceStartDate) {
        this.productwiseTaggedPriceVersionId = productwiseTaggedPriceVersionId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.taggedPriceStartDate=taggedPriceStartDate;
        this.taggedPriceEndaDate=new LocalDate(9999,12,31);
    }

    public ProductwiseTaggedPriceVersionId getProductwiseTaggedPriceVersionId() {
        return productwiseTaggedPriceVersionId;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public LocalDate getTaggedPriceStartDate() {
        return taggedPriceStartDate;
    }

    public LocalDate getTaggedPriceEndaDate() {
        return taggedPriceEndaDate;
    }

    public void setTaggedPriceEndaDate(LocalDate taggedPriceEndaDate) {
        this.taggedPriceEndaDate = taggedPriceEndaDate;
    }
}
