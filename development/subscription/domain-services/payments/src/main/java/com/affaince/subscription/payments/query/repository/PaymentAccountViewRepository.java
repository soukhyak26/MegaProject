package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.payments.query.view.PaymentAccountView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 7/9/2017.
 */
public interface PaymentAccountViewRepository extends CrudRepository<PaymentAccountView,String> {
}
