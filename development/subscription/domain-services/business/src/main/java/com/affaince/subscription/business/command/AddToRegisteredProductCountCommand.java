package com.affaince.subscription.business.command;

/**
 * Created by mandar on 4/16/2017.
 */
public class AddToRegisteredProductCountCommand {
    private final int businessAccountId;
    private final long registeredProductCount;

    public AddToRegisteredProductCountCommand(int businessAccountId,long registeredProductCount) {
        this.businessAccountId=businessAccountId;
        this.registeredProductCount = registeredProductCount;
    }

    public int getBusinessAccountId() {
        return businessAccountId;
    }

    public long getRegisteredProductCount() {
        return registeredProductCount;
    }
}
