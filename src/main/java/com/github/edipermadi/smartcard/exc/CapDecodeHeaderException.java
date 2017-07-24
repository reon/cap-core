package com.github.edipermadi.smartcard.exc;

public class CapDecodeHeaderException extends CapDecodeException {
    public CapDecodeHeaderException(String message) {
        super(message);
    }

    public CapDecodeHeaderException(String message, Throwable ex) {
        super(message, ex);
    }

    public static CapDecodeHeaderException invalidTag(final int tag) {
        return new CapDecodeHeaderException("unexpected CAP header tag " + tag);
    }

    public static CapDecodeHeaderException invalidSize() {
        return new CapDecodeHeaderException("invalid CAP header size");
    }

    public static CapDecodeHeaderException invalidMagic() {
        return new CapDecodeHeaderException("invalid CAP header magic");
    }

    public static CapDecodeHeaderException invalidPackageAID() {
        return new CapDecodeHeaderException("invalid CAP header package AID");
    }

    public static CapDecodeHeaderException invalidPackageNameLength() {
        return new CapDecodeHeaderException("invalid CAP header package name length");
    }

    public static CapDecodeHeaderException invalidPackageName() {
        return new CapDecodeHeaderException("invalid CAP header package name");
    }

    public static CapDecodeHeaderException invalidAidLength() {
        return new CapDecodeHeaderException("invalid CAP header AID length");
    }
}
