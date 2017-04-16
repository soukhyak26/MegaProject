package com.affaince.subscription.business.command.event;

/**
 * Created by mandar on 4/16/2017.
 */
public class RegisteredProductCountAddedToBusinessAccountEvent {
    private final int id;
    private final long registeredProductCount;
    public RegisteredProductCountAddedToBusinessAccountEvent(Integer id, long registeredProductCount) {
        this.id=id;
        this.registeredProductCount=registeredProductCount;
    }

    public int getId() {
        return id;
    }

    public long getRegisteredProductCount() {
        return registeredProductCount;
    }
}
