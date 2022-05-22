package org.reqres.exceptions;

@SuppressWarnings("serial")
public class ExceptionFactory extends RuntimeException{

    public ExceptionFactory(String message) {
        super(message);
    }

    public ExceptionFactory(String message, Throwable cause) {
        super(message, cause);
    }
}
