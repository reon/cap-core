package com.github.edipermadi.smartcard.exc;

public class CapException extends Exception {
    public CapException(String message, Throwable ex) {
        super(message, ex);
    }

    public CapException(String message) {
        super(message);
    }
}
