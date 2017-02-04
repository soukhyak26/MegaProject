package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.ProfitAccountTransactionView;
import com.affaince.subscription.business.query.view.ProfitAccountView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 03-02-2017.
 */
public interface ProfitAccountTransactionsViewRepository extends CrudRepository<ProfitAccountTransactionView,Long>{

}
