package com.affaince.subscription.product.web.request;

import com.affaince.subscription.product.vo.ProductTargetParameters;

/**
 * Created by mandar on 21-10-2016.
 */
public class SetTargetParameterRequest {
    ProductTargetParameters[] productTargetParameters;

    public ProductTargetParameters[] getProductTargetParameters() {
        return productTargetParameters;
    }

    public void setProductTargetParameters(ProductTargetParameters[] productTargetParameters) {
        this.productTargetParameters = productTargetParameters;
    }
}
