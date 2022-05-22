package org.reqres.exceptions;

@SuppressWarnings("serial")
public class InvalidSchemaFilePathException extends ExceptionFactory{
    public InvalidSchemaFilePathException(String message) {
        super(message);
    }

    public InvalidSchemaFilePathException(String message, Throwable cause) {
        super(message, cause);
    }
}
