package com.priceelasticity.pricedescrimination;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public double calculatePrice(Customer customer) {
        return Math.min(customer.getMaxPrice(), cost);
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
