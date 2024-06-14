package payments.dummy;

public class DummyEvent1 {
    private double subscriptionValue;
    private int subscriptionPeriod;
    private int totalDeliveries;

    public DummyEvent1(double subscriptionValue, int subscriptionPeriod, int totalDeliveries) {
        this.subscriptionValue = subscriptionValue;
        this.subscriptionPeriod = subscriptionPeriod;
        this.totalDeliveries = totalDeliveries;
    }

    public double getSubscriptionValue() {
        return subscriptionValue;
    }

    public int getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public int getTotalDeliveries() {
        return totalDeliveries;
    }
}
