package com.github.edipermadi.smartcard;

/**
 * CAP builder class
 *
 * @author Edi Permadi
 */
public final class CapBuilder {
    private Cap.Header header;

    /**
     * Set CAP header
     *
     * @param header cap header
     * @return this instance
     */
    public CapBuilder setHeader(Cap.Header header) {
        if (header == null) {
            throw new IllegalArgumentException("CAP header is null");
        }
        this.header = header;
        return this;
    }

    /**
     * Build instance of {@link Cap}
     *
     * @return instance of {@link Cap}
     */
    public Cap build() {
        if (header == null) {
            throw new IllegalStateException("CAP header is mandatory");
        }

        return new Cap() {
            @Override
            public Header getHeader() {
                return header;
            }
        };
    }
}
