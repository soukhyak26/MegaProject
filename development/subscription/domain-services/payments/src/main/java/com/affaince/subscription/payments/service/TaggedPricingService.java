package com.affaince.subscription.payments.service;

import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.affaince.subscription.payments.exception.MultipleActiveOfferPricesException;
import com.affaince.subscription.payments.exception.MultipleActiveTaggedPricesException;
import com.affaince.subscription.payments.query.repository.ProductOfferPricesViewRepository;
import com.affaince.subscription.payments.query.repository.ProductTaggedPricesViewRepository;
import com.affaince.subscription.payments.query.view.ProductOfferPricesView;
import com.affaince.subscription.payments.query.view.ProductTaggedPricesView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 5/27/2017.
 */
@Component
public class TaggedPricingService {
    private ProductTaggedPricesViewRepository productTaggedPricesViewRepository;

    @Autowired
    public TaggedPricingService(ProductTaggedPricesViewRepository productTaggedPricesViewRepository) {
        this.productTaggedPricesViewRepository = productTaggedPricesViewRepository;
    }

    public double findLatestTaggedPriceForAProduct(String productId) {
        List<ProductTaggedPricesView> productWiseLatestTaggedPrices = productTaggedPricesViewRepository.findByProductwiseTaggedPriceVersionId_ProductIdAndTaggedPriceEndaDate(productId, new LocalDate(9999, 12, 31));
        if (productWiseLatestTaggedPrices.size() > 1) {
            throw MultipleActiveTaggedPricesException.build(productId);
        } else {
            return productWiseLatestTaggedPrices.get(0).getMRP();
        }
    }

    public double findTaggedPriceByProductIdAndTaggedPriceId(String productId, String taggedPriceVersionId) {
        ProductTaggedPricesView productIdTaggedPriceIdWiseView = productTaggedPricesViewRepository.findOne(new ProductwiseTaggedPriceVersionId(productId, taggedPriceVersionId));
        return productIdTaggedPriceIdWiseView.getMRP();
    }
}
