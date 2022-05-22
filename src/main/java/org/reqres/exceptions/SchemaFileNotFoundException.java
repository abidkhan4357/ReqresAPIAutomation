package org.reqres.exceptions;

@SuppressWarnings("serial")
public class SchemaFileNotFoundException extends ExceptionFactory{

    public SchemaFileNotFoundException(String message) {
        super(message);
    }

    public SchemaFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}

