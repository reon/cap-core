package com.github.edipermadi.smartcard.exc;

/**
 * CAP directory decoding exception
 *
 * @author Edi Permadi
 */
public final class CapDecodeDirectoryException extends CapDecodeException {
    public CapDecodeDirectoryException(String message, Throwable ex) {
        super(message, ex);
    }

    public CapDecodeDirectoryException(String message) {
        super(message);
    }

    public static CapDecodeDirectoryException invalidTag(final int tag) {
        return new CapDecodeDirectoryException("unexpected CAP directory tag " + tag);
    }

    public static CapDecodeDirectoryException invalidSize() {
        return new CapDecodeDirectoryException("invalid CAP directory size");
    }

    public static CapDecodeException invalidComponentTag() {
        return new CapDecodeDirectoryException("invalid custom component tag");
    }

    public static CapDecodeException truncatedComponent() {
        return new CapDecodeDirectoryException("component is truncated");
    }
}
