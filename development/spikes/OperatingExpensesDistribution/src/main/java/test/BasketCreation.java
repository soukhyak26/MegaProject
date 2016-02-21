package test;

import java.util.*;

/**
 * Created by rsavaliya on 21/2/16.
 */
public class BasketCreation {
    private static Product[] products = new Product[6];
    private static List<Basket> baskets = new ArrayList<>(45000);
    private static Map <Double, Integer> deliveryChargesPerRuleCategory = new HashMap<>();
    private static double totalDeliveryCosOfAllBaskets = 0;

    static {
        products[0] = new Product("WP", 20, 0.2);
        products[1] = new Product("Sugar", 40, 1);
        products[2] = new Product("Milk", 50, 0.5);
        products[3] = new Product("butter", 50, 0.2);
        products[4] = new Product("WF", 70, 2);
        products[5] = new Product("TT", 80, 0.2);
        //deliveryChargesPerRuleCategory.put(0.0, 0);
        deliveryChargesPerRuleCategory.put(1.0, 5);
        deliveryChargesPerRuleCategory.put(2.0, 9);
        deliveryChargesPerRuleCategory.put(3.0, 13);
        deliveryChargesPerRuleCategory.put(4.0, 16);
        deliveryChargesPerRuleCategory.put(5.0, 20);
        deliveryChargesPerRuleCategory.put(6.0, 22);
        deliveryChargesPerRuleCategory.put(7.0, 24);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 45000; i++) {
            Basket basket = new Basket();
            List<Item> items = new ArrayList<>();
            double totalPurchasePrice = 0;
            double totalWeight = 0;
            for (int j = 0; j < products.length; j++) {
                Random random = new Random();
                int randomInt = random.nextInt(10);
                totalPurchasePrice = totalPurchasePrice + products[j].getPurchasePrice() * randomInt;
                totalWeight = totalWeight + products [j].getWeight()*randomInt;
                items.add(new Item(products[j].getId(), randomInt, products[j].getPurchasePrice()));
            }
            //items.parallelStream().filter(item -> item.getQuantity() == 0).collect();
            if (totalWeight == 0 || totalWeight > 7) continue;
            totalWeight = Math.ceil(totalWeight);
            basket.setItems(items);
            basket.setTotalPurchasePrice(totalPurchasePrice);
            basket.setTotalWeight(totalWeight);
            totalDeliveryCosOfAllBaskets = totalDeliveryCosOfAllBaskets + deliveryChargesPerRuleCategory.get(totalWeight);
            basket.setTotalDeliveryCharges(deliveryChargesPerRuleCategory.get(totalWeight));
            basket.calculateItemLevelDeliveryCharges ();
            baskets.add(basket);
            System.out.println(basket.getTotalPurchasePrice() + "  " + basket.getTotalWeight());

        }
        calculateProductLevelDistribution ();
    }

    private static void calculateProductLevelDistribution() {
        Map <String, Double> deliveryChargesPerProduct = new HashMap<>();
        Map <String, Integer> totalNumberOfSubscriptionPerProduct = new HashMap<>();
        for (Basket basket:baskets) {
            for (Item item:basket.getItems()) {
                if (deliveryChargesPerProduct.get(item.getItemId()) != null) {
                    double deliveryCostOfItem = item.getDeliveryCharges() +
                            deliveryChargesPerProduct.get(item.getItemId());
                   /// double totalNumberOfSubscription = 0;
                    int totalNumberOfSubscription = item.getQuantity() + totalNumberOfSubscriptionPerProduct.get(item.getItemId());
                    deliveryChargesPerProduct.put(item.getItemId(), deliveryCostOfItem);
                    totalNumberOfSubscriptionPerProduct.put(item.getItemId(), totalNumberOfSubscription);
                } else {
                    deliveryChargesPerProduct.put(item.getItemId(), item.getDeliveryCharges());
                    totalNumberOfSubscriptionPerProduct.put(item.getItemId(), item.getQuantity());
                }
            }
        }
        for (String productId:deliveryChargesPerProduct.keySet()) {
            System.out.println(productId + " " + deliveryChargesPerProduct.get(productId) + " " + totalNumberOfSubscriptionPerProduct.get(productId));
            System.out.println(productId + " Per Unit Price " + deliveryChargesPerProduct.get(productId)/totalNumberOfSubscriptionPerProduct.get(productId));
        }
        System.out.println(totalDeliveryCosOfAllBaskets);
    }
}