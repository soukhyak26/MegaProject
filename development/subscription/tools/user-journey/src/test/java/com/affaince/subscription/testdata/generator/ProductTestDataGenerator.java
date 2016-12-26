package com.affaince.subscription.testdata.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
    }

    private static void generateProductDetailsCsvFile() throws IOException {
        File file = new File("d:/productdetails.csv");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
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

    public static void main(String[] args) throws IOException {
        ProductTestDataGenerator.generate(5);
    }
}
