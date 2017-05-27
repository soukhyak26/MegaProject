package com.affaince.subscription.payments.service;

import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.affaince.subscription.payments.exception.MultipleActiveOfferPricesException;
import com.affaince.subscription.payments.query.repository.ProductOfferPricesViewRepository;
import com.affaince.subscription.payments.query.view.ProductOfferPricesView;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 5/27/2017.
 */
@Component
public class OfferPricingService {
    private ProductOfferPricesViewRepository productOfferPricesViewRepository;
    @Autowired
    public OfferPricingService(ProductOfferPricesViewRepository productOfferPricesViewRepository) {
        this.productOfferPricesViewRepository = productOfferPricesViewRepository;
    }

    public double findLatestOfferPriceOrPercentForAProduct(String productId){
        List<ProductOfferPricesView> productWiseActivePriceBuckets=productOfferPricesViewRepository.findByProductwisePriceBucketId_ProductIdAndEndDate(productId,new LocalDateTime(9999,12,31,23,59,00));
        if(productWiseActivePriceBuckets.size()>1){
            throw MultipleActiveOfferPricesException.build(productId);
        }else{
            return productWiseActivePriceBuckets.get(0).getOfferPriceOrPercent();
        }
    }

    public double findOfferPriceOrPercentByProductAndPriceBucket(String productId,String priceBucketId){
        ProductOfferPricesView productAndPriceBucketWiseView=productOfferPricesViewRepository.findOne(new ProductwisePriceBucketId(productId,priceBucketId));
        return productAndPriceBucketWiseView.getOfferPriceOrPercent();
    }
}
