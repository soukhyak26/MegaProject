package com.affaince.subscription.testdata.generator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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
    }

    private static void generateProductDetailsCsvFile() throws IOException {
        File file = new File("d:/productdetails.json");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            //fileOutputStream.write(("productId,productName,categoryID,subCategoryId,quantity,quantityUnit,substitutes,complements\n").getBytes());
            products.forEach(product -> {
                try {
                    /*fileOutputStream.write(
                            (product.getProductId() + "," +
                                    product.getProductName() + "," +
                                    product.getCategoryId() + "," +
                                    product.getSubCategoryId() + "," +
                                    product.getQuantity() + "," +
                                    product.getQuantityUnit() + "," +
                                    product.getSubstitute().toString() + "," +
                                    product.getComplements().toString() + "\n").getBytes()


                    );*/
                    ProductDetails productDetails = new ProductDetails(
                            product.getProductId(),product.getProductName(),product.getCategoryId(),
                            product.getSubCategoryId(),product.getQuantity(),product.getQuantityUnit(),
                            product.getSubstitute(),product.getComplements()
                    );
                    ObjectMapper objectMapper = new ObjectMapper();
                    fileOutputStream.write(objectMapper.writeValueAsBytes(productDetails));
                    fileOutputStream.write(("\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void generatePriceDetails () throws IOException {
        File file = new File("d:/openingpricedetails.csv");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("productId,openingPrice,purchasePrice,MRP\n").getBytes());
            products.forEach(product -> {
                int purchasePrice = new Random().nextInt(product.getMaxPrice()-product.getMinPrice()) +
                        product.getMinPrice();
                int profitMargin = new Random().nextInt(product.getMaxProfitMargin()-product.getMinProfitMargin()) +
                        product.getMinProfitMargin();
                int MRP = purchasePrice + (purchasePrice*profitMargin)/100;
                int openingPrice = new Random().nextInt(MRP - purchasePrice) + purchasePrice;
                try {
                    fileOutputStream.write((product.getProductId()+","+openingPrice + "," + purchasePrice + "," + MRP + "\n").getBytes());
                } catch (IOException e) {

                }
            });
        }
    }

    private static void generateStepForecast () throws IOException {
        File file = new File("d:/stepforecast.csv");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("productId,stepForecast1,stepForecast2,stepForecast3,stepForecast4,stepForecast5,stepForecast6\n").getBytes());
            products.forEach(product -> {
                try {
                    fileOutputStream.write((product.getProductId()+",").getBytes());
                } catch (IOException e) {
                }
                LocalDate startDate = LocalDate.now();
                LocalDate endDate = LocalDate.now();
                int newSubscription = 500;
                int churnSubscription = 20;
                int purchasePrice = new Random().nextInt(product.getMaxPrice()-product.getMinPrice()) +
                        product.getMinPrice();
                int profitMargin = new Random().nextInt(product.getMaxProfitMargin()-product.getMinProfitMargin()) +
                        product.getMinProfitMargin();
                int MRP = purchasePrice + (purchasePrice*profitMargin)/100;
                int openingPrice = new Random().nextInt(MRP - purchasePrice) + purchasePrice;
                for (int i=1;i<=6;i++) {
                    startDate = endDate.plusDays(1);
                    endDate = startDate.plusDays(product.getActualsAggregationPeriodForTargetForecast());
                    newSubscription = newSubscription + (newSubscription*(new Random().nextInt(product.getMaxPercentageIncreaseInForecast()
                        - product.getMinPercentageIncreaseInForecast())+product.getMinPercentageIncreaseInForecast()))/newSubscription;
                    try {
                        fileOutputStream.write((
                                "["+startDate+","+
                                endDate+","+
                                purchasePrice+","+
                                MRP+","+
                                newSubscription+","+
                                churnSubscription+"]").getBytes());
                        if (i!=6) {
                            fileOutputStream.write((",").getBytes());
                        }
                    } catch (IOException e) {
                    }
                }
                try {
                    fileOutputStream.write(("\n").getBytes());
                } catch (IOException e) {

                }
            });
        }
    }

    public static void main(String[] args) throws IOException {
        ProductTestDataGenerator.generate(5);
    }
}
