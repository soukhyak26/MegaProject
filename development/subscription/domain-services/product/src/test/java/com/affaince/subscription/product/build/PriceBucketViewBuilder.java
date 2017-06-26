package com.affaince.subscription.product.build;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.affaince.subscription.common.vo.ProductwiseTaggedPriceVersionId;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.TaggedPriceVersionsViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.TaggedPriceVersionsView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mandar on 6/25/2017.
 */
public class PriceBucketViewBuilder {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    TaggedPriceVersionsViewRepository taggedPriceVersionsViewRepository;

    public void buildPriceBucketView() throws FileNotFoundException,IOException {
        priceBucketViewRepository.deleteAll();
        List<PriceBucketView> priceBucketViews = new ArrayList<>(365);
        Set<TaggedPriceVersionsView> taggedPriceVersionViews= new HashSet<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/priceBucketViewSim.csv"))))) {
            List<List<String>> values = fileReader.lines().map(line -> Arrays.asList(line.trim().split(","))).collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-yy");
            for (int i=0;i<11;i++) {
                List<String> value=values.get(i);
                ProductwisePriceBucketId productwisePriceBucketId = new ProductwisePriceBucketId(value.get(0), value.get(0)+"_"+ LocalDate.parse(value.get(7), formatter).toString());
                ProductPricingCategory productPricingCategory= ProductPricingCategory.valueOf(Integer.parseInt(value.get(1)));

                PriceTaggedWithProduct taggedPriceVersion= new PriceTaggedWithProduct(value.get(2),Integer.parseInt(value.get(3)),Integer.parseInt(value.get(4)),LocalDate.parse(value.get(5), formatter));
                taggedPriceVersion.setTaggedEndDate(LocalDate.parse(value.get(6), formatter));

                TaggedPriceVersionsView taggedPriceVersionsView= new TaggedPriceVersionsView(new ProductwiseTaggedPriceVersionId(value.get(0),value.get(2)),Integer.parseInt(value.get(3)),Integer.parseInt(value.get(4)),LocalDate.parse(value.get(5), formatter),LocalDate.parse(value.get(6), formatter));
                taggedPriceVersionViews.add(taggedPriceVersionsView);

                LocalDateTime priceBucketStartDate=LocalDateTime.parse(value.get(7), formatter);
                LocalDateTime priceBucketEndDate=LocalDateTime.parse(value.get(8), formatter);
                double offerPrice=Double.parseDouble(value.get(9));
                long newSubscriptionCount = Long.parseLong(value.get(10));
                long churnedSubscriptionCount = Long.parseLong(value.get(11));
                long totalNumberOfExistingSubscriptionCount = Long.parseLong(value.get(12));
                PriceBucketView priceBucketView = new PriceBucketView(productwisePriceBucketId,productPricingCategory);
                priceBucketView.setTaggedPriceVersion(taggedPriceVersion);
                priceBucketView.setFromDate(priceBucketStartDate);
                priceBucketView.setToDate(priceBucketEndDate);
                priceBucketView.setOfferedPriceOrPercentDiscountPerUnit(offerPrice);
                priceBucketView.setNumberOfNewSubscriptionsAssociatedWithAPrice(newSubscriptionCount);
                priceBucketView.setNumberOfChurnedSubscriptionsAssociatedWithAPrice(churnedSubscriptionCount);
                priceBucketView.setNumberOfExistingSubscriptionsAssociatedWithAPrice(totalNumberOfExistingSubscriptionCount);
                priceBucketView.setEntityStatus(EntityStatus.ACTIVE);
                priceBucketViews.add(priceBucketView);
            }
            priceBucketViewRepository.save(priceBucketViews);
            taggedPriceVersionsViewRepository.save(taggedPriceVersionViews);
        }
    }



}
