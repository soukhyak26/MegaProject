package com.affaince.subscription.common.upload.exception;

/**
 * Created by mandar on 9/21/2017.
 */
public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
