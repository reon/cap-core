package com.github.edipermadi.smartcard;

import java.io.InputStream;

public interface CapDecoder {
    void decode(InputStream stream);
}
