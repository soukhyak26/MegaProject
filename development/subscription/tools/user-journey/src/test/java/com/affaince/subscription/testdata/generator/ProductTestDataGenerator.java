package com.affaince.subscription.testdata.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by rbsavaliya on 26-12-2016.
 */
public class ProductTestDataGenerator {

    private static List <Product> products;

    public static void generate (int size) throws IOException {
        products = ProductBuilder.buildProducts(500).quantity().
                branded().
                complements().
                substitutes().
                minPrice().
                maxPrice().
                minProfit().
                maxProfit().build();
        generateProductDetailsCsvFile ();
        generatePriceDetails();
    }

    private static void generateProductDetailsCsvFile() throws IOException {
        File file = new File("d:/productdetails.csv");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("productId,productName,categoryID,subCategoryId,quantity,quantityUnit,substitutes,complements\n").getBytes());
            products.forEach(product -> {
                try {
                    fileOutputStream.write(
                            (product.getProductId() + "," +
                                    product.getProductName() + "," +
                                    product.getCategoryId() + "," +
                                    product.getSubCategoryId() + "," +
                                    product.getQuantity() + "," +
                                    product.getQuantityUnit() + "," +
                                    product.getSubstitute().toString() + "," +
                                    product.getComplements().toString() + "\n").getBytes()


                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void generatePriceDetails () throws IOException {
        File file = new File("d:/openingpricedetails.csv");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(("openingPrice,purchasePrice,MRP\n").getBytes());
            products.forEach(product -> {
                int purchasePrice = new Random().nextInt(product.getMaxPrice()-product.getMinPrice()) +
                        product.getMinPrice();
                int profitMargin = new Random().nextInt(product.getMaxProfitMargin()-product.getMinProfitMargin()) +
                        product.getMinProfitMargin();
                int MRP = purchasePrice + (purchasePrice*profitMargin)/100;
                int openingPrice = new Random().nextInt(MRP - purchasePrice) + purchasePrice;
                try {
                    fileOutputStream.write((openingPrice + "," + purchasePrice + "," + MRP + "\n").getBytes());
                } catch (IOException e) {

                }
            });
        }
    }

    private static void generateStepForecast () {

    }

    public static void main(String[] args) throws IOException {
        ProductTestDataGenerator.generate(5);
    }
}
