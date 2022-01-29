package com.albo.deserialization.exception;

public class ConfigManagerException extends Exception {
    public ConfigManagerException(String message) {
        super(message);
    }

    public ConfigManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigManagerException(Throwable cause) {
        super(cause);
    }
}
