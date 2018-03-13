package com.affiance.business.exception;

/**
 * Created by anayonkar on 31/7/16.
 */
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
