package com.cloudsherpas.droolsample.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author RMPader
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,
                reason = "Encountered an internal error. Please contact system administrators.")
public class SystemException extends RuntimeException {

    public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
