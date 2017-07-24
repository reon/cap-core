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
    private Cap.Applet applet;

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
        if (directory == null) {
            throw new IllegalArgumentException("CAP directory is null");
        }
        this.directory = directory;
        return this;
    }

    /**
     * Set CAP applet component
     *
     * @param applet applet component
     * @return this instance
     */
    public CapBuilder setApplet(final Cap.Applet applet) {
        if (applet == null) {
            throw new IllegalArgumentException("CAP applet is null");
        }
        this.applet = applet;
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

        @SerializedName("applet")
        private final CapAppletBuilder.CapApplet applet;

        /**
         * Class constructor
         *
         * @param builder CAP builder object
         */
        CapImpl(final CapBuilder builder) {
            this.header = (CapHeaderBuilder.CapHeader) builder.header;
            this.directory = (CapDirectoryBuilder.CapDirectory) builder.directory;
            this.applet = (CapAppletBuilder.CapApplet)builder.applet;
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
        public Applet getApplet() {
            return applet;
        }

        @Override
        public String toString() {
            return gson.toJson(this);
        }
    }
}
