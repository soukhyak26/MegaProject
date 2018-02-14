package com.affaince.subscription.testdata.generator;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.repository.DefaultIdGenerator;
import com.affaince.subscription.repository.IdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
            byte [] values = new byte[fileInputStream.available()];
            fileInputStream.read(values);
            subscriptionCount = new Integer(new String(values));
            System.out.println(subscriptionCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subscriptionCount;
    }

    public static void main(String[] args) throws IOException {
        new ProductTestDataGenerator().generate(4).getSubscriptionCount();
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
        writeProductIdsToFile ();
        return this;
    }

    private void writeProductIdsToFile() {
        File file = new File(classLoader.getResource(".").getPath() + "/productids");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            products.forEach(product -> {
                    try {
                        String IDString = product.getProductName() + "$" +
                                product.getCategoryId() + "$" +
                                product.getSubCategoryId() + "$" +
                                product.getQuantity();
                        fileOutputStream.write((new DefaultIdGenerator().generator(IDString) + "\n").getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void createSysDateAndTime() {
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
    }*/

    private void writeSubscriptionCountToFile() {
        File file = new File(classLoader.getResource(".").getPath() + "/subscriptioncount");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write((subscriptionCount+"").getBytes());
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
            int newSubscription = 5000;
            int churnSubscription = 200;
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
                endDate = startDate.dayOfMonth().withMaximumValue();
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
}
