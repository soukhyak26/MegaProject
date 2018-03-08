package com.test.verifier.domains.business.exception;

public class ProvisionNotCreatedException extends RuntimeException {
    public ProvisionNotCreatedException() {
    }

    public ProvisionNotCreatedException(String message) {
        super(message);
    }

    public ProvisionNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
