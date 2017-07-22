package com.github.edipermadi.smartcard;

/**
 * CAP Object Interface
 *
 * @author Edi Permadi
 */
public interface Cap {
    /**
     * Get CAP header
     *
     * @return CAP header
     */
    Header getHeader();

    /**
     * CAP header interface
     *
     * @author Edi Permadi
     */
    interface Header {
        /**
         * Get CAP version encoded in 0xaabb (major, minor)
         *
         * @return CAP header version
         */
        int getVersion();

        /**
         * Get CAP header flags
         *
         * @return CAP header flags
         */
        int getFlags();

        /**
         * Get CAP package info
         *
         * @return CAP package info
         */
        PackageInfo getPackageInfo();

        /**
         * Get CAP package name info
         *
         * @return CAP package name info
         */
        PackageNameInfo getPackageNameInfo();

        /**
         * CAP package info interface
         *
         * @author Edi Permadi
         */
        interface PackageInfo {
            /**
             * Get package info version encoded in 0xaabb (major, minor)
             *
             * @return CAP package info version
             */
            int getVersion();

            /**
             * Get CAP package AID
             *
             * @return CAP package AID
             */
            byte[] getAID();
        }

        /**
         * CAP package name info interface
         *
         * @author Edi Permadi
         */
        interface PackageNameInfo {
            String getName();
        }
    }
}
