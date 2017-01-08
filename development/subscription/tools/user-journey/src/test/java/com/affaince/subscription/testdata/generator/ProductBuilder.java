package com.affaince.subscription.testdata.generator;

import java.util.*;

/**
 * Created by rbsavaliya on 24-12-2016.
 */
public class ProductBuilder {

    private static List<Product> products = new ArrayList<>();
    private int size;


    private ProductBuilder(int size) {
        this.size = size;
    }

    public static ProductBuilder buildProducts(int size) {
        ProductBuilder productBuilder = new ProductBuilder(size);
        for (int i = 0; i < size; i++) {
            Product product = new Product(
                     i + "",
                    "productName" + i,
                    "cat" + i % 20,
                    "subcat" + (i % 20 % (new Random().nextInt(5) + 3))
            );
            products.add(product);
        }
        return productBuilder;
    }

    public ProductBuilder quantity() {
        products.forEach(product -> {
            product.setQuantity(new Random().nextInt(5000) + 50);
            product.setQuantityUnit("gram");
        });
        return this;
    }

    public ProductBuilder substitutes() {
        Map<String, List<String>> map = new HashMap<>();
        products.
                forEach(product -> map.computeIfAbsent(product.getCategoryId() +
                        "-" + product.getSubCategoryId(), k -> new ArrayList<>()).add(product.getProductId()));
        products.forEach(product -> {
            product.setSubstitute(map.get(product.getCategoryId() +
                    "-" + product.getSubCategoryId()));
        });
        return this;
    }

    public ProductBuilder complements() {
        Map<String, List<String>> map = new HashMap<>();
        products.
                forEach(product -> map.computeIfAbsent(product.getCategoryId(),
                        k -> new ArrayList<>()).add(product.getProductId()));
        products.forEach(product -> {
            product.setComplements(map.get(product.getCategoryId()));
        });
        return this;
    }

    public ProductBuilder branded() {
        products.forEach(product -> {
            if (new Random().nextInt(6) == 4) {
                product.setBranded(true);
            }
        });
        return this;
    }

    public ProductBuilder minPrice() {
        products.forEach(product -> {
            if (product.isBranded()) {
                product.setMinPrice(100);
            } else {
                product.setMinPrice(20);
            }
        });
        return this;
    }

    public ProductBuilder maxPrice() {
        products.forEach(product -> {
            if (product.isBranded()) {
                product.setMaxPrice(700);
            } else {
                product.setMaxPrice(400);
            }
        });
        return this;
    }

    public ProductBuilder minProfit() {
        products.forEach(product -> {
            if (product.isBranded()) {
                product.setMinProfitMargin(10);
            } else {
                product.setMinProfitMargin(30);
            }
        });
        return this;
    }

    public ProductBuilder maxProfit() {
        products.forEach(product -> {
            if (product.isBranded()) {
                product.setMaxProfitMargin(40);
            } else {
                product.setMaxProfitMargin(100);
            }
        });
        return this;
    }

    public ProductBuilder minPercentageIncreaseInForecast() {
        products.forEach(product -> {
            if (product.isBranded()) {
                product.setMinPercentageIncreaseInForecast(10);
            } else {
                product.setMinPercentageIncreaseInForecast(20);
            }
        });
        return this;
    }

    public ProductBuilder maxPercentageIncreaseInForecast() {
        products.forEach(product -> {
            if (product.isBranded()) {
                product.setMaxPercentageIncreaseInForecast(50);
            } else {
                product.setMaxPercentageIncreaseInForecast(100);
            }
        });
        return this;
    }

    public ProductBuilder actualsAggregationPeriodForTargetForecast () {
        products.forEach(product -> {
            product.setActualsAggregationPeriodForTargetForecast(30);
        });
        return this;
    }

    public ProductBuilder percentageChangeInTrend () {
        products.forEach(product -> {
            product.setPercentageChangeInTrend(new Random().nextInt(40)+(-20));
        });
        return this;
    }

    public static void main(String[] args) {
        ProductBuilder.buildProducts(100).substitutes().complements().branded().percentageChangeInTrend();
        products.forEach(product -> System.out.println(product.getPercentageChangeInTrend()));
    }

    public List<Product> build() {
        return products;
    }
}
