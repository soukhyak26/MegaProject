package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.BudgetChangeRecommendationView;
import com.verifier.domains.business.vo.RecommendationReason;
import com.verifier.domains.business.vo.RecommendationReceiver;
import com.verifier.domains.business.vo.RecommenderType;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 18-02-2017.
 */
public interface BusinessBudgetChangeRecommendationViewRepository extends CrudRepository<BudgetChangeRecommendationView, Long> {
    public List<BudgetChangeRecommendationView> findAll();
    public List<BudgetChangeRecommendationView> findByRecommendationDate(LocalDate date);
    public List<BudgetChangeRecommendationView> findByBusinessAccountIdAndRecommenderIdAndRecommendationDateAndRecommenderTypeAndRecommendationReasonAndRecommendationReceiver(String businessAccountId, String recommenderId, LocalDate recommendationDate, RecommenderType recommenderType, RecommendationReason recommendationReason, RecommendationReceiver recommendationReceiver);

}
