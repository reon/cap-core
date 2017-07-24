package com.github.edipermadi.smartcard;


import com.github.edipermadi.smartcard.exc.CapException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class CapDecoderTest {
    @Test
    public void testHello() throws IOException, CapException {
        final File file = new File("src/test/resources/ykneo-oath-1.0.0.cap");
        Reporter.log("parsing file " + file.getAbsolutePath(), true);
        final FileInputStream fis = new FileInputStream(file);
        final CapDecoder decoder = new CapDecoderImpl();
        final Cap cap = decoder.decode(fis);
        Assert.assertNotNull(cap);
        Reporter.log(cap.toString(), true);
    }
}
