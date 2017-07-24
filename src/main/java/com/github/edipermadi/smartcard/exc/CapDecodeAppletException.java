package com.github.edipermadi.smartcard.exc;

/**
 * CAP applet decoding exception
 *
 * @author Edi Permadi
 */
public final class CapDecodeAppletException extends CapDecodeException {
    /**
     * Class constructor
     *
     * @param message exception message
     * @param cause   exception cause
     */
    public CapDecodeAppletException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Class constructor
     *
     * @param message exception message
     */
    public CapDecodeAppletException(final String message) {
        super(message);
    }

    public static CapDecodeAppletException invalidTag(int componentTag) {
        return new CapDecodeAppletException("unexpected CAP applet tag " + componentTag);
    }

    public static CapDecodeAppletException invalidSize() {
        return new CapDecodeAppletException("invalid CAP applet size");
    }

    public static CapDecodeAppletException invalidAppletCount() {
        return new CapDecodeAppletException("invalid CAP applet count");
    }

    public static CapDecodeAppletException invalidAIDLength() {
        return new CapDecodeAppletException("invalid CAP applet AID length");
    }

    public static CapDecodeAppletException invalidAID() {
        return new CapDecodeAppletException("invalid CAP applet AID");
    }

    public static CapDecodeException invalidInstallMethodOffset() {
        return new CapDecodeAppletException("invalid CAP install method offset");
    }
}
