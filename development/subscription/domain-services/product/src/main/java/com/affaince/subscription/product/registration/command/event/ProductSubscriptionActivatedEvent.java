package com.affaince.subscription.product.registration.command.event;

import com.affaince.subscription.common.vo.ProductStatistics;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 10-01-2016.
 */
public class ProductSubscriptionActivatedEvent {
     private final String productId;
     private final int subscriptionCount;
     private final LocalDate subscriptionActivationDate;

     public ProductSubscriptionActivatedEvent(String productId, int subscriptionCount, LocalDate subscriptionActivationDate) {
          this.productId = productId;
          this.subscriptionCount = subscriptionCount;
          this.subscriptionActivationDate = subscriptionActivationDate;
     }

     public String getProductId() {
          return productId;
     }

     public int getSubscriptionCount() {
          return subscriptionCount;
     }

     public LocalDate getSubscriptionActivationDate() {
          return subscriptionActivationDate;
     }
}
