package com.affaince.subscription.product.build;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.product.query.repository.PriceBucketTransactionViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketTransactionView;
import com.affaince.subscription.product.vo.PriceBucketTransactionId;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mandar on 6/26/2017.
 */
public class PriceBucketTransactionViewBuilder {
    @Autowired
    PriceBucketTransactionViewRepository priceBucketTransactionViewRepository;

    public void buildPriceBucketTransactionView() throws FileNotFoundException,IOException {
        priceBucketTransactionViewRepository.deleteAll();
        List<PriceBucketTransactionView> priceBucketTransactionViews = new ArrayList<>(365);
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/priceBucketTransactionViewSim.csv"))))) {
            List<List<String>> values = fileReader.lines().map(line -> Arrays.asList(line.trim().split(","))).collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMM-yy");
            for (int i=0;i<11;i++) {
                List<String> value=values.get(i);
                PriceBucketTransactionId priceBucketTransactionId = new PriceBucketTransactionId(value.get(0), value.get(0)+"_"+ LocalDate.parse(value.get(1), formatter).toString(),LocalDate.parse(value.get(2), formatter));
                long newSubscriptionCount = Long.parseLong(value.get(4));
                long churnedSubscriptionCount = Long.parseLong(value.get(5));
                long totalNumberOfExistingSubscriptionCount = Long.parseLong(value.get(6));
                PriceBucketTransactionView priceBucketTransactionView = new PriceBucketTransactionView(priceBucketTransactionId);
                priceBucketTransactionView.setNumberOfNewSubscriptions(newSubscriptionCount);
                priceBucketTransactionView.setNumberOfChurnedSubscriptions(churnedSubscriptionCount);
                priceBucketTransactionView.setNumberOfExistingSubscriptions(totalNumberOfExistingSubscriptionCount);
                priceBucketTransactionViews.add(priceBucketTransactionView);
            }
            priceBucketTransactionViewRepository.save(priceBucketTransactionViews);
        }
    }

}
