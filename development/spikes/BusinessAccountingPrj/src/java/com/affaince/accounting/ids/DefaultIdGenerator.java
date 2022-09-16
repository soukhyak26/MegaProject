package com.affaince.accounting.ids;

import java.util.UUID;

public class DefaultIdGenerator implements IdGenerator {
    @Override
    public String generator() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String generator(String parameterString) {
        return UUID.nameUUIDFromBytes(parameterString.getBytes()).toString();
    }

}
