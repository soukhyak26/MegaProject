package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/26/2017.
 */
@Document(collection="ProductTaggedPricesView")
public class ProductTaggedPricesView {
    private ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId;
    private double purchasePricePerUnit;
    private double MRP;

    public ProductTaggedPricesView(ProductwiseTaggedPriceVersionId productwiseTaggedPriceVersionId, double purchasePricePerUnit, double MRP) {
        this.productwiseTaggedPriceVersionId = productwiseTaggedPriceVersionId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
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

}
