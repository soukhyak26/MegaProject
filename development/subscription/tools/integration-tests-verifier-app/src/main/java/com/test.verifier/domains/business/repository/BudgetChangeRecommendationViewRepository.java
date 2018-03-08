package com.test.verifier.domains.business.repository;

import com.test.verifier.domains.business.view.BudgetChangeRecommendationView;
import com.test.verifier.domains.business.vo.RecommendationReason;
import com.test.verifier.domains.business.vo.RecommendationReceiver;
import com.test.verifier.domains.business.vo.RecommenderType;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 18-02-2017.
 */
public interface BudgetChangeRecommendationViewRepository extends CrudRepository<BudgetChangeRecommendationView, Long> {
    public List<BudgetChangeRecommendationView> findAll();

    public List<BudgetChangeRecommendationView> findByBusinessAccountIdAndRecommenderIdAndRecommendationDateAndRecommenderTypeAndRecommendationReasonAndRecommendationReceiver(String businessAccountId, String recommenderId, LocalDate recommendationDate, RecommenderType recommenderType, RecommendationReason recommendationReason, RecommendationReceiver recommendationReceiver);

}
