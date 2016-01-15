package com.affaince.subscription.simulation.web.controller;

import com.affaince.subscription.simulation.command.event.basketdispatch.BasketDispatchedStatusEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mandark on 06-09-2015.
 */
public class BasketDispatchedStatusService {

    List<BasketDispatchedStatusEvent> getDispatchedBaskets() {
        List<BasketDispatchedStatusEvent> basketDispatchedStatusEvents = new ArrayList();
        BasketDispatchedStatusEvent event1 = new BasketDispatchedStatusEvent("basket01", new Date(), 3, 4);
        BasketDispatchedStatusEvent event2 = new BasketDispatchedStatusEvent("basket02", new Date(), 3, 4);
        BasketDispatchedStatusEvent event3 = new BasketDispatchedStatusEvent("basket03", new Date(), 3, 4);
        return basketDispatchedStatusEvents;
    }
}
