package com.affaince.accounting.ledger.util;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.UUID;

public class VersionNumberGenerator {

    public static String generateVersionNumber(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        return UUID.fromString(LocalDateTime.now().toString(dateTimeFormatter)).toString();
    }
}
