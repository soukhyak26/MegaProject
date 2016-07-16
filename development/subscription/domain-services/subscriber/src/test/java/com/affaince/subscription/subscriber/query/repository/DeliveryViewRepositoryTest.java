package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.subscriber.Application;
import com.affaince.subscription.subscriber.query.view.DeliveryItem;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by rbsavaliya on 16-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class DeliveryViewRepositoryTest {
    @Autowired
    private DeliveryViewRepository deliveryViewRepository;
    private List <Product> products = new ArrayList<>();
    Random randomObj = new Random();
    private Map <Integer, Integer> weightWiseDeliveryCharges = new HashMap<>(20);
    @Before
    public void init() {

        createProducts ();

        for (int i=0; i<20; i++) {
            weightWiseDeliveryCharges.put(i, i*10);
        }

        int deliveryId = 0;
        for (int i=0; i<90; i++) {
            LocalDate deliveryDate = LocalDate.now().plusDays(i);
            Random perDayDeliveryRandomObj = new Random ();
            int perDayDeliveryCount = perDayDeliveryRandomObj.ints(20,50).findFirst().getAsInt();
            for (int j=0; j<perDayDeliveryCount; j++) {
                final DeliveryView deliveryView = new DeliveryView(deliveryId++ + "","1","1", null, deliveryDate, DeliveryStatus.CREATED);
                Random perDeliveryItemCountRandomObj = new Random ();
                int deliveryItemCount = perDeliveryItemCountRandomObj.ints(1, 10).findFirst().getAsInt();
                List <DeliveryItem> deliveryItems = new ArrayList<>(10);
                for (int k=0;k<deliveryItemCount;k++) {
                    DeliveryItem deliveryItem = new DeliveryItem();
                    Product product = products.get(randomObj.ints(1,50).findFirst().getAsInt());
                    deliveryItem.setWeightInGrms(product.getWeightInGrms());
                    deliveryItem.setOfferedPriceWithBasketLevelDiscount(product.getOfferedPriceWithBasketLevelDiscount());
                    deliveryItem.setDeliveryItemId(product.getDeliveryItemId());
                    deliveryItems.add(deliveryItem);
                }
                deliveryView.setDeliveryItems(deliveryItems);
                calculateItemLevelDeliveryCharges(deliveryView);
                deliveryViewRepository.save(deliveryView);
            }
        }
    }

    private void createProducts() {
        for (int i=1;i<=50;i++) {
            Product product = new Product();
            product.setDeliveryItemId(i+"");
            product.setWeightInGrms(randomObj.ints(100, 2000).findFirst().getAsInt());
            product.setOfferedPriceWithBasketLevelDiscount(
                    randomObj.ints(10, 200).findFirst().getAsInt()
            );
            products.add(product);
        }
    }

    private void calculateItemLevelDeliveryCharges(DeliveryView deliveryView) {
        double totalWeight = 0;
        double totalDeliveryPrice = 0;
        for (DeliveryItem deliveryItem: deliveryView.getDeliveryItems()) {
            totalWeight += deliveryItem.getWeightInGrms();
            totalDeliveryPrice += deliveryItem.getOfferedPriceWithBasketLevelDiscount();
        }
        totalWeight = totalWeight/1000;
        double totalDeliveryCharges = weightWiseDeliveryCharges.get(5);

        for (DeliveryItem item: deliveryView.getDeliveryItems()) {
            item.setDeliveryCharges((item.getOfferedPriceWithBasketLevelDiscount()*totalDeliveryCharges)/totalDeliveryPrice);
        }
    }

    @Test
    public void testDeliveryViewFindAll () {
        deliveryViewRepository.findAll().forEach(deliveryView -> System.out.println(deliveryView.getDeliveryId()));
    }
}
