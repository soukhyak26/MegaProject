package com.verifier.domains.fulfillment.view;


import com.verifier.domains.fulfillment.vo.OrderDetail;
import com.verifier.domains.fulfillment.vo.ProductOrderId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class ProductOrderView {
    @Id
    private ProductOrderId productOrderId;
    private List<OrderDetail> orderDetails;

    public ProductOrderId getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(ProductOrderId productOrderId) {
        this.productOrderId = productOrderId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
