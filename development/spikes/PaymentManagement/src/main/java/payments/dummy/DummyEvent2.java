package payments.dummy;

import java.util.List;

public class DummyEvent2 {
    private int subscriptionRenewalCount;
    private List<Integer> subscriptionPeriodPerSubscription;
    private int totalDeliveries;

    public DummyEvent2(int subscriptionRenewalCount, List<Integer> subscriptionPeriodPerSubscription, int totalDeliveries) {
        this.subscriptionRenewalCount = subscriptionRenewalCount;
        this.subscriptionPeriodPerSubscription = subscriptionPeriodPerSubscription;
        this.totalDeliveries = totalDeliveries;
    }

    public int getSubscriptionRenewalCount() {
        return subscriptionRenewalCount;
    }

    public List<Integer> getSubscriptionPeriodPerSubscription() {
        return subscriptionPeriodPerSubscription;
    }

    public int getTotalDeliveries() {
        return totalDeliveries;
    }
}
