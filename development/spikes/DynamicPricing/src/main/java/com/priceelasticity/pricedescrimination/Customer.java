package com.priceelasticity.pricedescrimination;

public class Customer {
    private String name;
    private double maxPrice;

    public Customer(String name, double maxPrice) {
        this.name = name;
        this.maxPrice = maxPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public String getName() {
        return name;
    }
}
