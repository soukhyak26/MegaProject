package com.affaince.subscription.testdata.generator;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.repository.DefaultIdGenerator;
import com.affaince.subscription.repository.IdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.FileBackedOutputStream;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Created by rbsavaliya on 26-12-2016.
 */
public class ProductTestDataGenerator {

    private static List<Product> products;
    private static int subscriptionCount;
    private ClassLoader classLoader = getClass().getClassLoader();

    public int getSubscriptionCount() {
        File file = new File(classLoader.getResource(".").getPath() + "/subscriptioncount");
        try (InputStream fileInputStream = new FileInputStream(file)) {
            subscriptionCount =  fileInputStream.read();
            System.out.println(subscriptionCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subscriptionCount;
    }

    public static void main(String[] args) throws IOException {
        new ProductTestDataGenerator().generate(1).getSubscriptionCount();
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
        writeSubscriptionCountToFile ();
        createSysDateAndTime ();
        return this;
    }

    private void createSysDateAndTime() {
        File file = new File (classLoader.getResource(".").getPath() + "/sysdate.csv");
        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write("sysDate,sysDateTime".getBytes());
            fileOutputStream.write("\n".getBytes());
            for (int i=0; i<subscriptionCount; i++) {
                //for (int j=0;j<5;j++) {
                    fileOutputStream.write(formatter.print(LocalDate.now().plusDays(i)).getBytes());
                    fileOutputStream.write(",".getBytes());
                    fileOutputStream.write(dateTimeFormatter.print(LocalDateTime.now().plusDays(i)).getBytes());
                    fileOutputStream.write("\n".getBytes());
                //}
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSubscriptionCountToFile() {
        File file = new File(classLoader.getResource(".").getPath() + "/subscriptioncount");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(subscriptionCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateProductDetailsCsvFile() throws IOException {
        File file = new File(classLoader.getResource(".").getPath() + "/productdetails.json");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("[").getBytes());
            products.forEach(product -> {
                try {
                    double purchasePrice = new Random().doubles(product.getMinPrice(), product.getMaxPrice()).findAny().getAsDouble();
                    double mrp = purchasePrice*(1+product.getMaxProfitMargin()/100);
                    ProductDetails productDetails = new ProductDetails(
                            product.getProductId(), product.getProductName(), product.getCategoryId(),
                            product.getSubCategoryId(), product.getQuantity(), product.getQuantityUnit(),
                            product.getSubstitute(), product.getComplements(), purchasePrice, mrp
                    );
                    product.setProductDetails (productDetails);
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
                double openingPrice = new Random().doubles(product.getProductDetails().getPurchasePrice(), product.getProductDetails().getMRP())
                        .findAny().getAsDouble();

                try {
                    PriceDetails priceDetails = new PriceDetails(openingPrice);
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
            product.setgeneratedProductId (productId);
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
            ProductForecastParameter productForecastParameters[] = new ProductForecastParameter[12];
            for (int i = 1; i <= 12; i++) {
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
            File file = new File(classLoader.getResource(".").getPath() + "/Subscribers.json");
            OutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(objectMapper.writeValueAsBytes(subscribers));
            /*Files.write(Paths.get(classLoader.getResource(".").toURI().toString() + "/Subscribers.json"),
                    objectMapper.writeValueAsBytes(subscribers),
                    StandardOpenOption.CREATE);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateSubscriptionData() throws JsonProcessingException {
        Map<String, Integer> lineNumberTracker = new HashMap<>();
        Map<String, SubscriptionItem> subscriptionItemMap = new HashMap<>();
        products.forEach(product -> {
            long totalBasketsToBeCreated = product.getForecasts().get(0).getNumberOfNewSubscriptions()
                    + (product.getForecasts().get(0).getNumberOfNewSubscriptions() * product.getPercentageChangeInTrend() / 100);
            ObjectMapper objectMapper = new ObjectMapper();
            int i = 0;
            while (totalBasketsToBeCreated >= 0) {
                int noOfCycle = new Random().nextInt(9) + 3;
                BasketItemRequest basketItemRequest = new BasketItemRequest(
                        product.getGeneratedProductId(),
                        1,
                        new Period(1, PeriodUnit.MONTH),
                        product.getForecasts().get(0).getMRP(),
                        product.getForecasts().get(0).getMRP(),
                        noOfCycle
                );
                String subscriberId = new DefaultIdGenerator().generator("testemail" + i + "@affaince.com");
                String fileName = classLoader.getResource(".").getPath() + "/" + subscriberId + ".json";
                if (lineNumberTracker.get(fileName) != null &&
                        lineNumberTracker.get(fileName).intValue() == 20) {
                    i++;
                    continue;
                }
                totalBasketsToBeCreated -= noOfCycle;
                if (subscriptionItemMap.get(fileName) != null) {
                    subscriptionItemMap.get(fileName).getBasketItemRequests().add(basketItemRequest);
                } else {
                    SubscriptionItem subscriptionItem = new SubscriptionItem();
                    subscriptionItem.setBasketItemRequests(new ArrayList<>());
                    subscriptionItemMap.put(fileName, subscriptionItem);
                    subscriptionItemMap.get(fileName).getBasketItemRequests().add(basketItemRequest);
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

        });
        ObjectMapper objectMapper = new ObjectMapper();
        for (String fileName:
             subscriptionItemMap.keySet()) {
            File file = new File(fileName);
            try {
                OutputStream outputStream = new FileOutputStream(file);
                String s = objectMapper.writeValueAsString(subscriptionItemMap.get(fileName));
                outputStream.write(s.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
