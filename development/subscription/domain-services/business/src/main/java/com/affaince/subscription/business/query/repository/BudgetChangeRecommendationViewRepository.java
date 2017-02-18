package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.BudgetChangeRecommendationView;
import com.affaince.subscription.business.vo.RecommendationReason;
import com.affaince.subscription.business.vo.RecommendationReceiver;
import com.affaince.subscription.business.vo.RecommenderType;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 18-02-2017.
 */
public interface BudgetChangeRecommendationViewRepository extends CrudRepository<BudgetChangeRecommendationView, Long> {
    public List<BudgetChangeRecommendationView> findAll();

    public List<BudgetChangeRecommendationView> findByBusinessAccountIdAndRecommenderIdAndRecommendationDateAndRecommenderTypeAndRecommendationReasonAndRecommendationReceiver(Integer businessAccountId, String recommenderId, LocalDate recommendationDate, RecommenderType recommenderType, RecommendationReason recommendationReason, RecommendationReceiver recommendationReceiver);

}
