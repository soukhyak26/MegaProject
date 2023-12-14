package com.priceelasticity.pricedescrimination;

import java.util.ArrayList;
import java.util.List;

public class ThirdDegreePriceDiscrimination {

    public static void main(String[] args) {
        // Create different customer segments
        List<Customer> segmentA = new ArrayList<>();
        segmentA.add(new Customer("Customer A", 50.0));
        segmentA.add(new Customer("Customer B", 70.0));

        List<Customer> segmentB = new ArrayList<>();
        segmentB.add(new Customer("Customer C", 90.0));
        segmentB.add(new Customer("Customer D", 60.0));

        Product product = new Product("Product X", 40.0);

        // Set prices based on third-degree price discrimination
        for (Customer customer : segmentA) {
            double price = product.calculatePrice(customer);
            System.out.println(customer.getName() + " is charged $" + price + " for " + product.getName());
        }

        for (Customer customer : segmentB) {
            double price = product.calculatePrice(customer);
            System.out.println(customer.getName() + " is charged $" + price + " for " + product.getName());
        }
    }
}
