package com.affaince.subscription.product.build;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mandar on 6/25/2017.
 */
@Component
public class ProductActualsViewBuilder {
    @Autowired
    ProductActualsViewRepository productActualsViewRepository;

    public void buildProductActualsView() throws FileNotFoundException,IOException{
        productActualsViewRepository.deleteAll();
        List<ProductActualsView> productActualsViews = new ArrayList<>(365);
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/actualViewSim.csv"))))) {
            List<List<String>> values = fileReader.lines().map(line -> Arrays.asList(line.trim().split(","))).collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
            for (int i=0;i<11;i++) {
                List<String> value=values.get(i);
                ProductVersionId productVersionId = new ProductVersionId(value.get(0), LocalDate.parse(value.get(1), formatter));
                LocalDate endDate = LocalDate.parse(value.get(2), formatter);
                long newSubscriptionCount = Long.parseLong(value.get(3));
                long churnedSubscriptionCount = Long.parseLong(value.get(4));
                long totalNumberOfExistingSubscriptionCount = Long.parseLong(value.get(5));
                ProductActualsView productActualsView = new ProductActualsView(productVersionId, endDate, newSubscriptionCount, churnedSubscriptionCount, totalNumberOfExistingSubscriptionCount);
                productActualsViews.add(productActualsView);
            }
            productActualsViewRepository.save(productActualsViews);
        }
    }

    public void deleteProductActualsView(){
        productActualsViewRepository.deleteAll();
    }
}
