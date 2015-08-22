package com.affaince.subscription.repository;

import java.util.UUID;

/**
 * Created by rbsavaliya on 20-07-2015.
 */
public class DefaultIdGenerator implements IdGenerator {
    @Override
    public String generator() {
        return UUID.randomUUID().toString();
    }
}
