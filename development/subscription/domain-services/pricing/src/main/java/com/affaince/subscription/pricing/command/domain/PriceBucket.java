package com.affaince.subscription.pricing.command.domain;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 28-11-2015.
 */
public class PriceBucket {
    private double purchasePricePerUnit;
    private double salePricePerUnit;
    private double MRP;
    private LocalDate fromDate;
    private LocalDate toDate;
}
