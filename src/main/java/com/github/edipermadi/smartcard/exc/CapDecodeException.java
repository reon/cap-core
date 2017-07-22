package com.github.edipermadi.smartcard.exc;

import java.io.IOException;

public class CapDecodeException extends CapException {
    public CapDecodeException(String message, Throwable ex) {
        super(message, ex);
    }

    public CapDecodeException(String message) {
        super(message);
    }
}
