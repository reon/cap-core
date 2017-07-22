package com.github.edipermadi.smartcard;

import com.github.edipermadi.smartcard.exc.CapException;

import java.io.InputStream;

public interface CapDecoder {
    Cap decode(InputStream stream) throws CapException;
}
