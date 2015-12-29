package com.affaince.subscription.subscriber.services;

import org.springframework.beans.factory.annotation.Value;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rbsavaliya on 29-12-2015.
 */
public class Base64Encoder {

    @Value("${java.security.algorithm}")
    private static String algorithm;

    public static String generateHash (String text) throws NoSuchAlgorithmException {
        if (algorithm == null) {
            algorithm = "SHA-256";
        }
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(text.getBytes());
        return new BASE64Encoder().encode(messageDigest.digest());
    }
}
