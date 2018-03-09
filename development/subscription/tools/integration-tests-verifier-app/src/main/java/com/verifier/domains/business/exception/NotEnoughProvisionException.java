package com.verifier.domains.business.exception;

public class NotEnoughProvisionException extends Exception {
    private static final String message = "The provision is not engough ";
    public NotEnoughProvisionException(String message) {
        super(message);

    }

    public static NotEnoughProvisionException build() {
        return new NotEnoughProvisionException(String.format(message));
    }
}
