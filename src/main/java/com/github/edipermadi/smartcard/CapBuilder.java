package com.github.edipermadi.smartcard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * CAP builder class
 *
 * @author Edi Permadi
 */
public final class CapBuilder {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Cap.Header header;
    private Cap.Directory directory;

    /**
     * Set CAP header component
     *
     * @param header cap header component
     * @return this instance
     */
    public CapBuilder setHeader(final Cap.Header header) {
        if (header == null) {
            throw new IllegalArgumentException("CAP header is null");
        }
        this.header = header;
        return this;
    }

    /**
     * Set CAP directory component
     *
     * @param directory CAP directory component
     * @return this instance
     */
    public CapBuilder setDirectory(final Cap.Directory directory) {
        this.directory = directory;
        return this;
    }

    /**
     * Build instance of {@link Cap}
     *
     * @return instance of {@link Cap}
     */
    public Cap build() {
        if (header == null) {
            throw new IllegalStateException("CAP header component is mandatory");
        } else if (directory == null) {
            throw new IllegalStateException("CAP directory component is mandatory");
        }

        return new CapImpl(this);
    }

    /**
     * CAP object implementation
     *
     * @author Edi Permadi
     */
    static final class CapImpl implements Cap {
        @SerializedName("header")
        private final CapHeaderBuilder.CapHeader header;

        @SerializedName("directory")
        private final CapDirectoryBuilder.CapDirectory directory;

        /**
         * Class constructor
         *
         * @param builder CAP builder object
         */
        CapImpl(final CapBuilder builder) {
            this.header = (CapHeaderBuilder.CapHeader) builder.header;
            this.directory = (CapDirectoryBuilder.CapDirectory) builder.directory;
        }

        @Override
        public Header getHeader() {
            return header;
        }

        @Override
        public Directory getDirectory() {
            return directory;
        }

        @Override
        public String toString() {
            return gson.toJson(this);
        }
    }
}
