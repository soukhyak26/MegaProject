package com.affaince.subscription.distribution.db;

import com.affaince.subscription.common.type.ForecastContentStatus;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class DeliveryForecastViewRepositoryMock {
    private static final List<DeliveryForecastView> deliveryForecastViews = new ArrayList<>();

    public static void build() {
        buildFirstForecast();
        buildSecondForecast();
        buildThirdForecast();
        buildFourthForecast();
        buildFifthForecast();
        buildSixthForecast();
        buildSeventhForecast();
        buildEighthForecast();
    }

    public static void buildFirstForecast() {
        DeliveryForecastView viewWt11 = new DeliveryForecastView(
                new LocalDate(2023, 1, 1),
                new LocalDate(2023, 1, 31),
                new LocalDate(2023, 1, 1),
                0, 250, 100,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt11);
        DeliveryForecastView viewWt21 = new DeliveryForecastView(
                new LocalDate(2023, 1, 1),
                new LocalDate(2023, 1, 31),
                new LocalDate(2023, 1, 1),
                251, 500, 120,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt21);
        DeliveryForecastView viewWt31 = new DeliveryForecastView(
                new LocalDate(2023, 1, 1),
                new LocalDate(2023, 1, 31),
                new LocalDate(2023, 1, 1),
                501, 750, 160,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt31);
        DeliveryForecastView viewWt41 = new DeliveryForecastView(
                new LocalDate(2023, 1, 1),
                new LocalDate(2023, 1, 31),
                new LocalDate(2023, 1, 1),
                751, 1000, 200,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt41);
        DeliveryForecastView viewWt51 = new DeliveryForecastView(
                new LocalDate(2023, 1, 1),
                new LocalDate(2023, 1, 31),
                new LocalDate(2023, 1, 1),
                1000, 1250, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt51);
        DeliveryForecastView viewWt61 = new DeliveryForecastView(
                new LocalDate(2023, 1, 1),
                new LocalDate(2023, 1, 31),
                new LocalDate(2023, 1, 1),
                1251, 1500, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt61);
        DeliveryForecastView viewWt71 = new DeliveryForecastView(
                new LocalDate(2023, 1, 1),
                new LocalDate(2023, 1, 31),
                new LocalDate(2023, 1, 1),
                1501, 1750, 300,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt71);
        DeliveryForecastView viewWt81 = new DeliveryForecastView(
                new LocalDate(2023, 1, 1),
                new LocalDate(2023, 1, 31),
                new LocalDate(2023, 1, 1),
                1751, 2000, 400,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt81);
    }

    public static void buildSecondForecast() {
        DeliveryForecastView viewWt11 = new DeliveryForecastView(
                new LocalDate(2023, 2, 1),
                new LocalDate(2023, 2, 28),
                new LocalDate(2023, 1, 1),
                0, 250, 100,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt11);
        DeliveryForecastView viewWt21 = new DeliveryForecastView(
                new LocalDate(2023, 2, 1),
                new LocalDate(2023, 2, 28),
                new LocalDate(2023, 1, 1),
                251, 500, 120,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt21);
        DeliveryForecastView viewWt31 = new DeliveryForecastView(
                new LocalDate(2023, 2, 1),
                new LocalDate(2023, 2, 28),
                new LocalDate(2023, 1, 1),
                501, 750, 160,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt31);
        DeliveryForecastView viewWt41 = new DeliveryForecastView(
                new LocalDate(2023, 2, 1),
                new LocalDate(2023, 2, 28),
                new LocalDate(2023, 1, 1),
                751, 1000, 200,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt41);
        DeliveryForecastView viewWt51 = new DeliveryForecastView(
                new LocalDate(2023, 2, 1),
                new LocalDate(2023, 2, 28),
                new LocalDate(2023, 1, 1),
                1000, 1250, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt51);
        DeliveryForecastView viewWt61 = new DeliveryForecastView(
                new LocalDate(2023, 2, 1),
                new LocalDate(2023, 2, 28),
                new LocalDate(2023, 1, 1),
                1251, 1500, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt61);
        DeliveryForecastView viewWt71 = new DeliveryForecastView(
                new LocalDate(2023, 2, 1),
                new LocalDate(2023, 2, 28),
                new LocalDate(2023, 1, 1),
                1501, 1750, 300,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt71);
        DeliveryForecastView viewWt81 = new DeliveryForecastView(
                new LocalDate(2023, 2, 1),
                new LocalDate(2023, 2, 28),
                new LocalDate(2023, 1, 1),
                1751, 2000, 400,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt81);
    }

    public static void buildThirdForecast() {
        DeliveryForecastView viewWt11 = new DeliveryForecastView(
                new LocalDate(2023, 3, 1),
                new LocalDate(2023, 3, 31),
                new LocalDate(2023, 1, 1),
                0, 250, 100,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt11);
        DeliveryForecastView viewWt21 = new DeliveryForecastView(
                new LocalDate(2023, 3, 1),
                new LocalDate(2023, 3, 31),
                new LocalDate(2023, 1, 1),
                251, 500, 120,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt21);
        DeliveryForecastView viewWt31 = new DeliveryForecastView(
                new LocalDate(2023, 3, 1),
                new LocalDate(2023, 3, 31),
                new LocalDate(2023, 1, 1),
                501, 750, 160,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt31);
        DeliveryForecastView viewWt41 = new DeliveryForecastView(
                new LocalDate(2023, 3, 1),
                new LocalDate(2023, 3, 31),
                new LocalDate(2023, 1, 1),
                751, 1000, 200,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt41);
        DeliveryForecastView viewWt51 = new DeliveryForecastView(
                new LocalDate(2023, 3, 1),
                new LocalDate(2023, 3, 31),
                new LocalDate(2023, 1, 1),
                1000, 1250, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt51);
        DeliveryForecastView viewWt61 = new DeliveryForecastView(
                new LocalDate(2023, 3, 1),
                new LocalDate(2023, 3, 31),
                new LocalDate(2023, 1, 1),
                1251, 1500, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt61);
        DeliveryForecastView viewWt71 = new DeliveryForecastView(
                new LocalDate(2023, 3, 1),
                new LocalDate(2023, 3, 31),
                new LocalDate(2023, 1, 1),
                1501, 1750, 300,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt71);
        DeliveryForecastView viewWt81 = new DeliveryForecastView(
                new LocalDate(2023, 3, 1),
                new LocalDate(2023, 3, 31),
                new LocalDate(2023, 1, 1),
                1751, 2000, 400,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt81);
    }

    public static void buildFourthForecast() {
        DeliveryForecastView viewWt11 = new DeliveryForecastView(
                new LocalDate(2023, 4, 1),
                new LocalDate(2023, 4, 30),
                new LocalDate(2023, 1, 1),
                0, 250, 100,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt11);
        DeliveryForecastView viewWt21 = new DeliveryForecastView(
                new LocalDate(2023, 4, 1),
                new LocalDate(2023, 4, 30),
                new LocalDate(2023, 1, 1),
                251, 500, 120,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt21);
        DeliveryForecastView viewWt31 = new DeliveryForecastView(
                new LocalDate(2023, 4, 1),
                new LocalDate(2023, 4, 30),
                new LocalDate(2023, 1, 1),
                501, 750, 160,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt31);
        DeliveryForecastView viewWt41 = new DeliveryForecastView(
                new LocalDate(2023, 4, 1),
                new LocalDate(2023, 4, 30),
                new LocalDate(2023, 1, 1),
                751, 1000, 200,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt41);
        DeliveryForecastView viewWt51 = new DeliveryForecastView(
                new LocalDate(2023, 4, 1),
                new LocalDate(2023, 4, 30),
                new LocalDate(2023, 1, 1),
                1000, 1250, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt51);
        DeliveryForecastView viewWt61 = new DeliveryForecastView(
                new LocalDate(2023, 4, 1),
                new LocalDate(2023, 4, 30),
                new LocalDate(2023, 1, 1),
                1251, 1500, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt61);
        DeliveryForecastView viewWt71 = new DeliveryForecastView(
                new LocalDate(2023, 4, 1),
                new LocalDate(2023, 4, 30),
                new LocalDate(2023, 1, 1),
                1501, 1750, 300,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt71);
        DeliveryForecastView viewWt81 = new DeliveryForecastView(
                new LocalDate(2023, 4, 1),
                new LocalDate(2023, 4, 30),
                new LocalDate(2023, 1, 1),
                1751, 2000, 400,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt81);
    }

    public static void buildFifthForecast() {
        DeliveryForecastView viewWt11 = new DeliveryForecastView(
                new LocalDate(2023, 5, 1),
                new LocalDate(2023, 5, 31),
                new LocalDate(2023, 1, 1),
                0, 250, 100,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt11);
        DeliveryForecastView viewWt21 = new DeliveryForecastView(
                new LocalDate(2023, 5, 1),
                new LocalDate(2023, 5, 31),
                new LocalDate(2023, 1, 1),
                251, 500, 120,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt21);
        DeliveryForecastView viewWt31 = new DeliveryForecastView(
                new LocalDate(2023, 5, 1),
                new LocalDate(2023, 5, 31),
                new LocalDate(2023, 1, 1),
                501, 750, 160,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt31);
        DeliveryForecastView viewWt41 = new DeliveryForecastView(
                new LocalDate(2023, 5, 1),
                new LocalDate(2023, 5, 31),
                new LocalDate(2023, 1, 1),
                751, 1000, 200,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt41);
        DeliveryForecastView viewWt51 = new DeliveryForecastView(
                new LocalDate(2023, 5, 1),
                new LocalDate(2023, 5, 31),
                new LocalDate(2023, 1, 1),
                1000, 1250, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt51);
        DeliveryForecastView viewWt61 = new DeliveryForecastView(
                new LocalDate(2023, 5, 1),
                new LocalDate(2023, 5, 31),
                new LocalDate(2023, 1, 1),
                1251, 1500, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt61);
        DeliveryForecastView viewWt71 = new DeliveryForecastView(
                new LocalDate(2023, 5, 1),
                new LocalDate(2023, 5, 31),
                new LocalDate(2023, 1, 1),
                1501, 1750, 300,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt71);
        DeliveryForecastView viewWt81 = new DeliveryForecastView(
                new LocalDate(2023, 5, 1),
                new LocalDate(2023, 5, 31),
                new LocalDate(2023, 1, 1),
                1751, 2000, 400,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt81);
    }

    public static void buildSixthForecast() {
        DeliveryForecastView viewWt11 = new DeliveryForecastView(
                new LocalDate(2023, 6, 1),
                new LocalDate(2023, 6, 30),
                new LocalDate(2023, 1, 1),
                0, 250, 100,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt11);
        DeliveryForecastView viewWt21 = new DeliveryForecastView(
                new LocalDate(2023, 6, 1),
                new LocalDate(2023, 6, 30),
                new LocalDate(2023, 1, 1),
                251, 500, 120,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt21);
        DeliveryForecastView viewWt31 = new DeliveryForecastView(
                new LocalDate(2023, 6, 1),
                new LocalDate(2023, 6, 30),
                new LocalDate(2023, 1, 1),
                501, 750, 160,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt31);
        DeliveryForecastView viewWt41 = new DeliveryForecastView(
                new LocalDate(2023, 6, 1),
                new LocalDate(2023, 6, 30),
                new LocalDate(2023, 1, 1),
                751, 1000, 200,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt41);
        DeliveryForecastView viewWt51 = new DeliveryForecastView(
                new LocalDate(2023, 6, 1),
                new LocalDate(2023, 6, 30),
                new LocalDate(2023, 1, 1),
                1000, 1250, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt51);
        DeliveryForecastView viewWt61 = new DeliveryForecastView(
                new LocalDate(2023, 6, 1),
                new LocalDate(2023, 6, 30),
                new LocalDate(2023, 1, 1),
                1251, 1500, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt61);
        DeliveryForecastView viewWt71 = new DeliveryForecastView(
                new LocalDate(2023, 6, 1),
                new LocalDate(2023, 6, 30),
                new LocalDate(2023, 1, 1),
                1501, 1750, 300,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt71);
        DeliveryForecastView viewWt81 = new DeliveryForecastView(
                new LocalDate(2023, 6, 1),
                new LocalDate(2023, 6, 30),
                new LocalDate(2023, 1, 1),
                1751, 2000, 400,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt81);
    }

    public static void buildSeventhForecast() {
        DeliveryForecastView viewWt11 = new DeliveryForecastView(
                new LocalDate(2023, 7, 1),
                new LocalDate(2023, 7, 31),
                new LocalDate(2023, 1, 1),
                0, 250, 100,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt11);
        DeliveryForecastView viewWt21 = new DeliveryForecastView(
                new LocalDate(2023, 7, 1),
                new LocalDate(2023, 7, 31),
                new LocalDate(2023, 1, 1),
                251, 500, 120,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt21);
        DeliveryForecastView viewWt31 = new DeliveryForecastView(
                new LocalDate(2023, 7, 1),
                new LocalDate(2023, 7, 31),
                new LocalDate(2023, 1, 1),
                501, 750, 160,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt31);
        DeliveryForecastView viewWt41 = new DeliveryForecastView(
                new LocalDate(2023, 7, 1),
                new LocalDate(2023, 7, 31),
                new LocalDate(2023, 1, 1),
                751, 1000, 200,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt41);
        DeliveryForecastView viewWt51 = new DeliveryForecastView(
                new LocalDate(2023, 7, 1),
                new LocalDate(2023, 7, 31),
                new LocalDate(2023, 1, 1),
                1000, 1250, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt51);
        DeliveryForecastView viewWt61 = new DeliveryForecastView(
                new LocalDate(2023, 7, 1),
                new LocalDate(2023, 7, 31),
                new LocalDate(2023, 1, 1),
                1251, 1500, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt61);
        DeliveryForecastView viewWt71 = new DeliveryForecastView(
                new LocalDate(2023, 7, 1),
                new LocalDate(2023, 7, 31),
                new LocalDate(2023, 1, 1),
                1501, 1750, 300,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt71);
        DeliveryForecastView viewWt81 = new DeliveryForecastView(
                new LocalDate(2023, 7, 1),
                new LocalDate(2023, 7, 31),
                new LocalDate(2023, 1, 1),
                1751, 2000, 400,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt81);
    }

    public static void buildEighthForecast() {
        DeliveryForecastView viewWt11 = new DeliveryForecastView(
                new LocalDate(2023, 8, 1),
                new LocalDate(2023, 8, 31),
                new LocalDate(2023, 1, 1),
                0, 250, 100,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt11);
        DeliveryForecastView viewWt21 = new DeliveryForecastView(
                new LocalDate(2023, 8, 1),
                new LocalDate(2023, 8, 31),
                new LocalDate(2023, 1, 1),
                251, 500, 120,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt21);
        DeliveryForecastView viewWt31 = new DeliveryForecastView(
                new LocalDate(2023, 8, 1),
                new LocalDate(2023, 8, 31),
                new LocalDate(2023, 1, 1),
                501, 750, 160,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt31);
        DeliveryForecastView viewWt41 = new DeliveryForecastView(
                new LocalDate(2023, 8, 1),
                new LocalDate(2023, 8, 31),
                new LocalDate(2023, 1, 1),
                751, 1000, 200,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt41);
        DeliveryForecastView viewWt51 = new DeliveryForecastView(
                new LocalDate(2023, 8, 1),
                new LocalDate(2023, 8, 31),
                new LocalDate(2023, 1, 1),
                1000, 1250, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt51);
        DeliveryForecastView viewWt61 = new DeliveryForecastView(
                new LocalDate(2023, 8, 1),
                new LocalDate(2023, 8, 31),
                new LocalDate(2023, 1, 1),
                1251, 1500, 250,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt61);
        DeliveryForecastView viewWt71 = new DeliveryForecastView(
                new LocalDate(2023, 8, 1),
                new LocalDate(2023, 8, 31),
                new LocalDate(2023, 1, 1),
                1501, 1750, 300,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt71);
        DeliveryForecastView viewWt81 = new DeliveryForecastView(
                new LocalDate(2023, 8, 1),
                new LocalDate(2023, 8, 31),
                new LocalDate(2023, 1, 1),
                1751, 2000, 400,
                ForecastContentStatus.ACTIVE
        );
        deliveryForecastViews.add(viewWt81);
    }

    public static List<DeliveryForecastView> getDeliveryForecastViews() {
        return deliveryForecastViews;
    }
}
