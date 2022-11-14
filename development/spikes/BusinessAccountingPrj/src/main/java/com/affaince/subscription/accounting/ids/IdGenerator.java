package com.affaince.subscription.accounting.ids;

public interface IdGenerator {
    String generator();

    String generator(String parameterString);
}
