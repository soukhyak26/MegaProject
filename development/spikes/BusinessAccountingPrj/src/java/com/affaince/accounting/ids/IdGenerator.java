package com.affaince.accounting.ids;

public interface IdGenerator {
    String generator();

    String generator(String parameterString);
}
