package com.affaince.subscription.payments.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by anayonkar on 21/8/16.
 */
@Document(collection = "PaymentView")
public class PaymentView {
    @Id
    private String paymentId;
}
