package com.blas.blascommon.exceptions.types;

public class BadGatewayException extends RuntimeException {

    public BadGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadGatewayException(String message) {
        super(message);
    }

    public BadGatewayException(Throwable cause) {
        super(cause);
    }
}
