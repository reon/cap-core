package com.github.edipermadi.smartcard;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

/**
 * CAP header builder class
 *
 * @author Edi Permadi
 */
final class CapHeaderBuilder {
    private int headerVersion = -1;
    private int headerFlags = -1;
    private CapHeaderPackageInfo packageInfo;
    private CapHeaderPackageNameInfo packageName;

    /**
     * Set header version
     *
     * @param headerVersion header version
     * @return this instance
     */
    CapHeaderBuilder setHeaderVersion(final int headerVersion) {
        if (headerVersion < 0) {
            throw new IllegalArgumentException("invalid header version");
        }
        this.headerVersion = headerVersion;
        return this;
    }

    /**
     * Set header flags
     *
     * @param headerFlags header flags
     * @return this instance
     */
    CapHeaderBuilder setHeaderFlags(final int headerFlags) {
        if (headerFlags < 0) {
            throw new IllegalArgumentException("invalid header flags");
        }
        this.headerFlags = headerFlags;
        return this;
    }

    /**
     * Set package information
     *
     * @param version package info version
     * @param aid     package AID
     * @return this instance
     */
    CapHeaderBuilder setPackageInfo(final int version, final String aid) {
        if (version < 0) {
            throw new IllegalArgumentException("invalid package info version");
        } else if (StringUtils.isEmpty(aid)) {
            throw new IllegalArgumentException("invalid package AID");
        }
        this.packageInfo = new CapHeaderPackageInfo(version, aid);
        return this;
    }

    /**
     * Set package name
     *
     * @param name package name
     * @return this instance
     */
    CapHeaderBuilder setPackageName(final String name) {
        this.packageName = new CapHeaderPackageNameInfo(name);
        return this;
    }

    /**
     * Build CAP object
     *
     * @return CAP object
     */
    Cap.Header build() {
        if (headerVersion < 0) {
            throw new IllegalStateException("header version is mandatory");
        } else if (headerVersion < 0) {
            throw new IllegalStateException("header flags is mandatory");
        } else if (packageInfo == null) {
            throw new IllegalStateException("package info is mandatory");
        }

        return new CapHeader(this);
    }

    /**
     * CAP header component implementation
     *
     * @author Edi Permadi
     */
    static final class CapHeader implements Cap.Header {
        @SerializedName("version")
        private final int version;

        @SerializedName("flags")
        private final int flags;

        @SerializedName("package")
        CapHeaderPackageInfo packageInfo;

        @SerializedName("package_name")
        CapHeaderPackageNameInfo packageName;

        /**
         * Class constructor
         *
         * @param builder cap header builder object
         */
        CapHeader(CapHeaderBuilder builder) {
            this.version = builder.headerVersion;
            this.flags = builder.headerFlags;
            this.packageInfo = builder.packageInfo;
            this.packageInfo = builder.packageInfo;
            this.packageName = builder.packageName;
        }

        @Override
        public int getVersion() {
            return version;
        }

        @Override
        public int getFlags() {
            return flags;
        }

        @Override
        public PackageInfo getPackage() {
            return packageInfo;
        }

        @Override
        public PackageNameInfo getPackageName() {
            return packageName;
        }
    }

    /**
     * CAP package info implementation
     *
     * @author Edi Permadi
     */
    static final class CapHeaderPackageInfo implements Cap.Header.PackageInfo {
        @SerializedName("version")
        private final int version;

        @SerializedName("aid")
        private final String aid;

        /**
         * Class constructor
         * @param version information version
         * @param aid package AID
         */
        CapHeaderPackageInfo(final int version, final String aid) {
            this.version = version;
            this.aid = aid;
        }

        @Override
        public int getVersion() {
            return version;
        }

        @Override
        public String getAID() {
            return aid;
        }
    }

    /**
     * CAP package name info implementation
     *
     * @author Edi Permadi
     */
    static final class CapHeaderPackageNameInfo implements Cap.Header.PackageNameInfo {
        @SerializedName("name")
        private final String name;

        /**
         * Class constructor
         * @param name CAP package name
         */
        CapHeaderPackageNameInfo(final String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
