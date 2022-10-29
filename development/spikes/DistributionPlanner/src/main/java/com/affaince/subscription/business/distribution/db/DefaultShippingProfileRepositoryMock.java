package com.affaince.subscription.business.distribution.db;

import com.affaince.subscription.business.distribution.profiles.*;

import java.util.ArrayList;
import java.util.List;

public class DefaultShippingProfileRepositoryMock {
    private static final List<DefaultShippingProfile> merchantWiseShippingProfiles = new ArrayList<>();

    public static void build() {
        DistributionZone zone1 = new DistributionZone("1", "1");
        zone1.addToRates(new RatePerUnitWeight("11", 30, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone1.addToRates(new RatePerUnitWeight("12", 45, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone1.addToRates(new RatePerUnitWeight("13", 60, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone1.addToRates(new RatePerUnitWeight("14", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone1.addToRates(new RatePerUnitWeight("15", 90, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone1.addToRates(new RatePerUnitWeight("16", 90, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone1.addToRates(new RatePerUnitWeight("17", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZone zone2 = new DistributionZone("1", "2");
        zone2.addToRates(new RatePerUnitWeight("21", 25, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone2.addToRates(new RatePerUnitWeight("22", 40, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone2.addToRates(new RatePerUnitWeight("23", 50, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone2.addToRates(new RatePerUnitWeight("24", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone2.addToRates(new RatePerUnitWeight("25", 75, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone2.addToRates(new RatePerUnitWeight("26", 75, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone2.addToRates(new RatePerUnitWeight("27", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZone zone3 = new DistributionZone("1", "3");
        zone3.addToRates(new RatePerUnitWeight("31", 25, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone3.addToRates(new RatePerUnitWeight("32", 40, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone3.addToRates(new RatePerUnitWeight("33", 50, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone3.addToRates(new RatePerUnitWeight("34", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone3.addToRates(new RatePerUnitWeight("35", 75, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone3.addToRates(new RatePerUnitWeight("36", 75, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone3.addToRates(new RatePerUnitWeight("37", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZoneGroup zoneGroup1 = new DistributionZoneGroup("1", "1");
        zoneGroup1.addToDistributionZone(zone1);
        zoneGroup1.addToDistributionZone(zone2);
        zoneGroup1.addToDistributionZone(zone3);


        DistributionZone zone4 = new DistributionZone("1", "4");
        zone4.addToRates(new RatePerUnitWeight("41", 30, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone4.addToRates(new RatePerUnitWeight("42", 45, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone4.addToRates(new RatePerUnitWeight("43", 60, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone4.addToRates(new RatePerUnitWeight("44", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone4.addToRates(new RatePerUnitWeight("45", 90, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone4.addToRates(new RatePerUnitWeight("46", 90, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone4.addToRates(new RatePerUnitWeight("47", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZone zone5 = new DistributionZone("1", "5");
        zone5.addToRates(new RatePerUnitWeight("51", 25, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone5.addToRates(new RatePerUnitWeight("52", 40, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone5.addToRates(new RatePerUnitWeight("53", 50, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone5.addToRates(new RatePerUnitWeight("54", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone5.addToRates(new RatePerUnitWeight("55", 75, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone5.addToRates(new RatePerUnitWeight("56", 75, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone5.addToRates(new RatePerUnitWeight("57", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZone zone6 = new DistributionZone("1", "6");
        zone6.addToRates(new RatePerUnitWeight("61", 25, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone6.addToRates(new RatePerUnitWeight("62", 40, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone6.addToRates(new RatePerUnitWeight("63", 50, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone6.addToRates(new RatePerUnitWeight("64", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone6.addToRates(new RatePerUnitWeight("65", 75, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone6.addToRates(new RatePerUnitWeight("66", 75, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone6.addToRates(new RatePerUnitWeight("67", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZoneGroup zoneGroup2 = new DistributionZoneGroup("1", "2");
        zoneGroup2.addToDistributionZone(zone4);
        zoneGroup2.addToDistributionZone(zone5);
        zoneGroup2.addToDistributionZone(zone6);

        DistributionZone zone7 = new DistributionZone("1", "7");

        zone7.addToRates(new RatePerUnitWeight("71", 25, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone7.addToRates(new RatePerUnitWeight("72", 40, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone7.addToRates(new RatePerUnitWeight("73", 50, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone7.addToRates(new RatePerUnitWeight("74", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone7.addToRates(new RatePerUnitWeight("75", 75, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone7.addToRates(new RatePerUnitWeight("76", 75, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone7.addToRates(new RatePerUnitWeight("77", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZone zone8 = new DistributionZone("1", "8");
        zone8.addToRates(new RatePerUnitWeight("81", 25, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone8.addToRates(new RatePerUnitWeight("82", 40, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone8.addToRates(new RatePerUnitWeight("83", 50, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone8.addToRates(new RatePerUnitWeight("84", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone8.addToRates(new RatePerUnitWeight("85", 75, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone8.addToRates(new RatePerUnitWeight("86", 75, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone8.addToRates(new RatePerUnitWeight("87", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZoneGroup zoneGroup3 = new DistributionZoneGroup("1", "3");
        zoneGroup3.addToDistributionZone(zone7);
        zoneGroup3.addToDistributionZone(zone8);


        DistributionZone zone9 = new DistributionZone("1", "9");
        zone9.addToRates(new RatePerUnitWeight("91", 30, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone9.addToRates(new RatePerUnitWeight("92", 45, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone9.addToRates(new RatePerUnitWeight("93", 60, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone9.addToRates(new RatePerUnitWeight("94", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone9.addToRates(new RatePerUnitWeight("95", 90, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone9.addToRates(new RatePerUnitWeight("96", 90, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone9.addToRates(new RatePerUnitWeight("97", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZone zone10 = new DistributionZone("1", "10");
        zone10.addToRates(new RatePerUnitWeight("101", 25, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone10.addToRates(new RatePerUnitWeight("102", 40, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone10.addToRates(new RatePerUnitWeight("103", 50, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone10.addToRates(new RatePerUnitWeight("104", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone10.addToRates(new RatePerUnitWeight("105", 75, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone10.addToRates(new RatePerUnitWeight("106", 75, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone10.addToRates(new RatePerUnitWeight("107", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZone zone11 = new DistributionZone("1", "11");
        zone11.addToRates(new RatePerUnitWeight("111", 25, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone11.addToRates(new RatePerUnitWeight("112", 40, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone11.addToRates(new RatePerUnitWeight("113", 50, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone11.addToRates(new RatePerUnitWeight("114", 60, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone11.addToRates(new RatePerUnitWeight("115", 75, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone11.addToRates(new RatePerUnitWeight("116", 75, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone11.addToRates(new RatePerUnitWeight("117", 90, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZone zone12 = new DistributionZone("1", "12");
        zone12.addToRates(new RatePerUnitWeight("121", 45, 0, 250, UnitForRate.WEIGHT_IN_GRAMS));
        zone12.addToRates(new RatePerUnitWeight("122", 70, 251, 500, UnitForRate.WEIGHT_IN_GRAMS));
        zone12.addToRates(new RatePerUnitWeight("123", 70, 501, 750, UnitForRate.WEIGHT_IN_GRAMS));
        zone12.addToRates(new RatePerUnitWeight("124", 70, 751, 1000, UnitForRate.WEIGHT_IN_GRAMS));
        zone12.addToRates(new RatePerUnitWeight("125", 100, 1001, 1250, UnitForRate.WEIGHT_IN_GRAMS));
        zone12.addToRates(new RatePerUnitWeight("126", 100, 1251, 1500, UnitForRate.WEIGHT_IN_GRAMS));
        zone12.addToRates(new RatePerUnitWeight("127", 120, 1501, 1750, UnitForRate.WEIGHT_IN_GRAMS));

        DistributionZoneGroup zoneGroup4 = new DistributionZoneGroup("1", "4");
        zoneGroup4.addToDistributionZone(zone9);
        zoneGroup4.addToDistributionZone(zone10);
        zoneGroup4.addToDistributionZone(zone11);
        zoneGroup4.addToDistributionZone(zone12);

        DefaultShippingProfile shippingProfile = new DefaultShippingProfile("1", "1");
        shippingProfile.addToDistributionZoneGroup(zoneGroup1);
        shippingProfile.addToDistributionZoneGroup(zoneGroup2);
        shippingProfile.addToDistributionZoneGroup(zoneGroup3);
        shippingProfile.addToDistributionZoneGroup(zoneGroup4);
        merchantWiseShippingProfiles.add(shippingProfile);
    }

    public static List<DefaultShippingProfile> getMerchantWiseShippingProfiles() {
        return merchantWiseShippingProfiles;
    }
}
