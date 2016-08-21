package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.PaymentView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anayonkar on 21/8/16.
 */
public interface PaymentViewRepository extends CrudRepository<PaymentView, String> {
    PaymentView findById(String id);
}
