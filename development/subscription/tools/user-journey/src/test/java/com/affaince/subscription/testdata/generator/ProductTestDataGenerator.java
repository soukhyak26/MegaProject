package com.affaince.subscription.testdata.generator;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.repository.DefaultIdGenerator;
import com.affaince.subscription.repository.IdGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDate;

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

    private static List<Product> products;
    private int subscriptionCount;
    private ClassLoader classLoader = getClass().getClassLoader();

    public int getSubscriptionCount() {
        return subscriptionCount;
    }

    public static void main(String[] args) throws IOException {
        new ProductTestDataGenerator().generate(5);
    }

    public ProductTestDataGenerator generate(int size) throws IOException {
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
        generateProductDetailsCsvFile();
        generatePriceDetails();
        generateStepForecast();
        generateSubscriptionData();
        generateSubscriberData();
        return this;
    }

    private void generateProductDetailsCsvFile() throws IOException {
        File file = new File(classLoader.getResource(".").getPath() + "/productdetails.json");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("[").getBytes());
            products.forEach(product -> {
                try {
                    ProductDetails productDetails = new ProductDetails(
                            product.getProductId(), product.getProductName(), product.getCategoryId(),
                            product.getSubCategoryId(), product.getQuantity(), product.getQuantityUnit(),
                            product.getSubstitute(), product.getComplements()
                    );
                    ObjectMapper objectMapper = new ObjectMapper();
                    fileOutputStream.write(objectMapper.writeValueAsBytes(productDetails));
                    if (Integer.parseInt(product.getProductId()) + 1 != products.size()) {
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

    private void generatePriceDetails() throws IOException {
        File file = new File(classLoader.getResource(".").getPath() + "/openingpricedetails.json");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("[").getBytes());
            products.forEach(product -> {
                int purchasePrice = new Random().nextInt(product.getMaxPrice() - product.getMinPrice()) +
                        product.getMinPrice();
                int profitMargin = new Random().nextInt(product.getMaxProfitMargin() - product.getMinProfitMargin()) +
                        product.getMinProfitMargin();
                int MRP = purchasePrice + (purchasePrice * profitMargin) / 100;
                int openingPrice = new Random().nextInt(MRP - purchasePrice) + purchasePrice;
                try {
                    PriceDetails priceDetails = new PriceDetails(openingPrice, purchasePrice, MRP);
                    ObjectMapper objectMapper = new ObjectMapper();
                    fileOutputStream.write(objectMapper.writeValueAsBytes(priceDetails));
                    if (Integer.parseInt(product.getProductId()) + 1 != products.size()) {
                        fileOutputStream.write((",").getBytes());
                    }
                    fileOutputStream.write(("\n").getBytes());
                } catch (IOException e) {

                }
            });
            fileOutputStream.write(("]").getBytes());
        }
    }

    private void generateStepForecast() throws IOException {
        IdGenerator idGenerator = new DefaultIdGenerator();
        products.forEach(product -> {
            String IDString = product.getProductName() + "$" + product.getCategoryId() + "$" + product.getSubCategoryId() + "$" + product.getQuantity();
            final String productId = idGenerator.generator(IDString);
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = LocalDate.now();
            int newSubscription = 500;
            int churnSubscription = 20;
            int purchasePrice = new Random().nextInt(product.getMaxPrice() - product.getMinPrice()) +
                    product.getMinPrice();
            int profitMargin = new Random().nextInt(product.getMaxProfitMargin() - product.getMinProfitMargin()) +
                    product.getMinProfitMargin();
            int MRP = purchasePrice + (purchasePrice * profitMargin) / 100;
            int openingPrice = new Random().nextInt(MRP - purchasePrice) + purchasePrice;
            List<ProductForecastParameter> forecasts = new ArrayList<>(6);
            ProductForecastParameter productForecastParameters[] = new ProductForecastParameter[6];
            for (int i = 1; i <= 6; i++) {
                startDate = endDate.plusDays(1);
                endDate = startDate.plusDays(product.getActualsAggregationPeriodForTargetForecast());
                newSubscription = newSubscription + (newSubscription * (new Random().nextInt(product.getMaxPercentageIncreaseInForecast()
                        - product.getMinPercentageIncreaseInForecast()) + product.getMinPercentageIncreaseInForecast())) / newSubscription;
                ProductForecastParameter forecast =
                        new ProductForecastParameter(startDate, endDate, purchasePrice, MRP, newSubscription, churnSubscription, 1);
                productForecastParameters[i - 1] = forecast;
                forecasts.add(forecast);
            }
            Forecast forecast = new Forecast();
            forecast.setProductForecastParameters(productForecastParameters);
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(classLoader.getResource(".").getPath() + "/" + productId + ".json");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(objectMapper.writeValueAsBytes(forecast));
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setForecasts(forecasts);
        });
    }

    private void generateSubscriberData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Subscriber> subscribers = new ArrayList<>();
        for (int i = 0; i < this.subscriptionCount; i++) {
            Subscriber subscriber = new Subscriber("Mr", "TestSubscriber" + i, "", "lastName" + i,
                    "A1-504", "Casa 7", "Pune", "MH", "India", "411033",
                    "testemail" + i + "@affaince.com", new Random().nextLong() * 100000000L + "", ""
            );
            subscribers.add(subscriber);
        }
        try {
            Files.write(Paths.get(classLoader.getResource(".").getPath() + "/Subscribers.json"),
                    objectMapper.writeValueAsBytes(subscribers),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateSubscriptionData() {
        Map<String, Integer> lineNumberTracker = new HashMap<>();
        Map<String, List<SubscriptionItem>> subscriptionItemMap = new HashMap<>();
        products.forEach(product -> {
            long totalBasketsToBeCreated = product.getForecasts().get(0).getNumberOfNewSubscriptions()
                    + (product.getForecasts().get(0).getNumberOfNewSubscriptions() * product.getPercentageChangeInTrend() / 100);
            ObjectMapper objectMapper = new ObjectMapper();
            int i = 0;
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
                String fileName = classLoader.getResource(".").getPath() + "/subscription" + i + ".json";
                if (lineNumberTracker.get(fileName) != null &&
                        lineNumberTracker.get(fileName).intValue() == 20) {
                    i++;
                    continue;
                }
                totalBasketsToBeCreated -= noOfCycle;
                if (subscriptionItemMap.get(fileName) != null) {
                    subscriptionItemMap.get(fileName).add(subscriptionItem);
                } else {
                    List<SubscriptionItem> subscriptionItems = new ArrayList<>();
                    subscriptionItems.add(subscriptionItem);
                    subscriptionItemMap.put(fileName, subscriptionItems);
                }
                if (lineNumberTracker.containsKey(fileName)) {
                    lineNumberTracker.put(fileName,
                            lineNumberTracker.get(fileName).intValue() + 1);
                } else {
                    lineNumberTracker.put(fileName, 1);
                }
                i++;
            }
            this.subscriptionCount = subscriptionItemMap.size();
            subscriptionItemMap.forEach((s, subscriptionItems) -> {
                try {
                    Files.write(Paths.get(s),
                            objectMapper.writeValueAsBytes(subscriptionItems),
                            StandardOpenOption.CREATE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
