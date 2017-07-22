package com.github.edipermadi.smartcard;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;

public final class CapHeaderBuilder {
    private int headerVersion = -1;
    private int headerFlags = -1;
    private int packageInfoVersion = -1;
    private byte[] aid;
    String packageInfoName;

    public CapHeaderBuilder setHeaderVersion(final int headerVersion) {
        if (headerVersion < 0) {
            throw new IllegalArgumentException("invalid header version");
        }
        this.headerVersion = headerVersion;
        return this;
    }

    public CapHeaderBuilder setHeaderFlags(final int headerFlags) {
        if (headerFlags < 0) {
            throw new IllegalArgumentException("invalid header flags");
        }
        this.headerFlags = headerFlags;
        return this;
    }

    public CapHeaderBuilder setPackageInfoVersion(final int packageInfoVersion) {
        if (packageInfoVersion < 0) {
            throw new IllegalArgumentException("invalid package info version");
        }
        this.packageInfoVersion = packageInfoVersion;
        return this;
    }

    public CapHeaderBuilder setAid(final byte[] aid) {
        if (ArrayUtils.isEmpty(aid)) {
            throw new IllegalArgumentException("invalid package AID");
        }
        this.aid = aid;
        return this;
    }

    public CapHeaderBuilder setPackageInfoName(final String packageInfoName) {
        this.packageInfoName = packageInfoName;
        return this;
    }

    public Cap.Header build() {
        if (headerVersion < 0) {
            throw new IllegalStateException("header version is mandatory");
        } else if (headerVersion < 0) {
            throw new IllegalStateException("header flags is mandatory");
        } else if (packageInfoVersion < 0) {
            throw new IllegalStateException("package info version is mandatory");
        } else if (aid == null) {
            throw new IllegalStateException("package AID is mandatory");
        }

        return new Cap.Header() {
            @Override
            public int getVersion() {
                return headerVersion;
            }

            @Override
            public int getFlags() {
                return headerFlags;
            }

            @Override
            public PackageInfo getPackageInfo() {
                return new PackageInfo() {
                    @Override
                    public int getVersion() {
                        return packageInfoVersion;
                    }

                    @Override
                    public byte[] getAID() {
                        return aid;
                    }
                };
            }

            @Override
            public PackageNameInfo getPackageNameInfo() {
                return new PackageNameInfo() {
                    @Override
                    public String getName() {
                        return packageInfoName;
                    }
                };
            }

            @Override
            public String toString() {
                return new StringBuilder()
                        .append(String.format("header :%n"))
                        .append(String.format("  version : 0x%04x%n", headerVersion))
                        .append(String.format("  flags   : 0x%02x%n", headerFlags))
                        .append(String.format("  package_info%n"))
                        .append(String.format("    version : 0x%04x%n", packageInfoVersion))
                        .append(String.format("    AID     : %s%n", Hex.encodeHexString(aid)))
                        .append(String.format("  package_name_info%n"))
                        .append(String.format("    name     : %s%n", packageInfoName))
                        .toString();
            }
        };
    }
}
