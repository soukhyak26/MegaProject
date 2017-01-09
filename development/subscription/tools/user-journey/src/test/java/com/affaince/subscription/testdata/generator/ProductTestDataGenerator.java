package com.affaince.subscription.testdata.generator;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Created by rbsavaliya on 26-12-2016.
 */
public class ProductTestDataGenerator {

    private static List <Product> products;

    public static void generate (int size) throws IOException {
        products = ProductBuilder.buildProducts(size).quantity().
                branded().
                complements().
                substitutes().
                minPrice().
                maxPrice().
                minProfit().
                maxProfit().
                minPercentageIncreaseInForecast().
                maxPercentageIncreaseInForecast().
                actualsAggregationPeriodForTargetForecast().build();
        generateProductDetailsCsvFile ();
        generatePriceDetails();
        generateStepForecast();
        generateSubscriptionData();
    }

    private static void generateProductDetailsCsvFile() throws IOException {
        File file = new File("d:/productdetails.json");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("[").getBytes());
            products.forEach(product -> {
                try {
                    ProductDetails productDetails = new ProductDetails(
                            product.getProductId(),product.getProductName(),product.getCategoryId(),
                            product.getSubCategoryId(),product.getQuantity(),product.getQuantityUnit(),
                            product.getSubstitute(),product.getComplements()
                    );
                    ObjectMapper objectMapper = new ObjectMapper();
                    fileOutputStream.write(objectMapper.writeValueAsBytes(productDetails));
                    if (Integer.parseInt(product.getProductId())-1 != products.size()) {
                        fileOutputStream.write((",").getBytes());
                    }
                    fileOutputStream.write(("\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileOutputStream.write(("]").getBytes());
        }
    }

    private static void generatePriceDetails () throws IOException {
        File file = new File("d:/openingpricedetails.json");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("[").getBytes());
            products.forEach(product -> {
                int purchasePrice = new Random().nextInt(product.getMaxPrice()-product.getMinPrice()) +
                        product.getMinPrice();
                int profitMargin = new Random().nextInt(product.getMaxProfitMargin()-product.getMinProfitMargin()) +
                        product.getMinProfitMargin();
                int MRP = purchasePrice + (purchasePrice*profitMargin)/100;
                int openingPrice = new Random().nextInt(MRP - purchasePrice) + purchasePrice;
                try {
                    PriceDetails priceDetails = new PriceDetails(openingPrice,purchasePrice,MRP);
                    ObjectMapper objectMapper = new ObjectMapper();
                    fileOutputStream.write(objectMapper.writeValueAsBytes(priceDetails));
                    if (Integer.parseInt(product.getProductId())-1 != products.size()) {
                        fileOutputStream.write((",").getBytes());
                    }
                    fileOutputStream.write(("\n").getBytes());
                } catch (IOException e) {

                }
            });
            fileOutputStream.write(("]").getBytes());
        }
    }

    private static void generateStepForecast () throws IOException {
        File file = new File("d:/stepforecast.json");
        final DateTimeFormatter formatter =
                DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("[").getBytes());
            products.forEach(product -> {

                LocalDateTime startDate = LocalDateTime.now();//LocalDateTime.parse(LocalDateTime.now().toString("dd-MM-yyyy HH:mm:ss"), formatter);
                LocalDateTime endDate = LocalDateTime.now();//LocalDateTime.parse(LocalDateTime.now().toString("dd-MM-yyyy HH:mm:ss"), formatter);
                int newSubscription = 500;
                int churnSubscription = 20;
                int purchasePrice = new Random().nextInt(product.getMaxPrice()-product.getMinPrice()) +
                        product.getMinPrice();
                int profitMargin = new Random().nextInt(product.getMaxProfitMargin()-product.getMinProfitMargin()) +
                        product.getMinProfitMargin();
                int MRP = purchasePrice + (purchasePrice*profitMargin)/100;
                int openingPrice = new Random().nextInt(MRP - purchasePrice) + purchasePrice;
                List <Forecast> forecasts = new ArrayList<>(6);
                for (int i=1;i<=6;i++) {
                    startDate = endDate.plusDays(1);
                    endDate = startDate.plusDays(product.getActualsAggregationPeriodForTargetForecast());
                    newSubscription = newSubscription + (newSubscription*(new Random().nextInt(product.getMaxPercentageIncreaseInForecast()
                        - product.getMinPercentageIncreaseInForecast())+product.getMinPercentageIncreaseInForecast()))/newSubscription;
                    Forecast forecast = new Forecast(startDate, endDate, purchasePrice, MRP, newSubscription, churnSubscription);
                    forecasts.add(forecast);
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        fileOutputStream.write(objectMapper.writeValueAsBytes(forecast));
                        if (Integer.parseInt(product.getProductId())-1 != products.size()) {
                                fileOutputStream.write((",").getBytes());
                        }
                        fileOutputStream.write(("\n").getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                product.setForecasts(forecasts);
            });
            fileOutputStream.write(("]").getBytes());
        }
    }

    private static void generateSubscriptionData () {
        Map <String, Integer> lineNumberTracker = new HashMap<>();
        products.forEach(product -> {
            long totalBasketsToBeCreated = product.getForecasts().get(0).getNumberOfNewSubscriptions()
                    + (product.getForecasts().get(0).getNumberOfNewSubscriptions()*product.getPercentageChangeInTrend()/100);
            ObjectMapper objectMapper = new ObjectMapper();
            int i=0;
            while (totalBasketsToBeCreated >= 0) {
                int noOfCycle = new Random().nextInt(9) + 3;
                SubscriptionItem subscriptionItem = new SubscriptionItem(
                        product.getProductId(),
                        1,
                        new Period(1, PeriodUnit.MONTH),
                        product.getForecasts().get(0).getMRP(),
                        product.getForecasts().get(0).getMRP(),
                        noOfCycle
                );
                if (lineNumberTracker.get("d:/abc/subscription"+i+".json")!= null &&
                        lineNumberTracker.get("d:/abc/subscription"+i+".json").intValue() == 20) {
                    i++;
                    continue;
                }
                totalBasketsToBeCreated -= noOfCycle;
                try {
                    Files.write(Paths.get("d:/abc/subscription"+i+".json"),
                            objectMapper.writeValueAsBytes(subscriptionItem),
                            StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.APPEND);
                    Files.write(Paths.get("d:/abc/subscription"+i+".json"),
                            "\n".getBytes(),
                            StandardOpenOption.APPEND);

                    if (lineNumberTracker.containsKey("d:/abc/subscription"+i+".json")) {
                        lineNumberTracker.put("d:/abc/subscription"+i+".json",
                                lineNumberTracker.get("d:/abc/subscription"+i+".json").intValue()+1);
                    } else {
                        lineNumberTracker.put("d:/abc/subscription"+i+".json", 1);
                    }
                    i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        ProductTestDataGenerator.generate(100);
    }
}
