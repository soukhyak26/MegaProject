package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 2/23/2017.
 */
@Component
public class ForecastFinderService {
    private final ProductForecastViewRepository productForecastViewRepository;

    @Autowired
    public ForecastFinderService(ProductForecastViewRepository productForecastViewRepository) {
        this.productForecastViewRepository = productForecastViewRepository;
    }

    public List<ProductForecastView> findForecastsBetweenDates(String productId,LocalDate startDate, LocalDate endDate) {
        return this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, startDate, endDate);
    }

    public List<ProductForecastView> findForecastsEarlierThan(String productId,LocalDate endDate) {
        Sort endDateSort = new Sort(Sort.Direction.DESC, "endDate");
        return this.productForecastViewRepository.findByProductVersionId_ProductIdAndEndDateLessThan(productId, endDate, endDateSort);
    }
}
