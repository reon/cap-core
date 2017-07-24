package com.github.edipermadi.smartcard;

import java.util.List;

/**
 * CAP Object Interface
 *
 * @author Edi Permadi
 */
public interface Cap {
    /**
     * Get CAP header component
     *
     * @return CAP header component
     */
    Header getHeader();

    /**
     * Get CAP directory component
     *
     * @return CAP directory component
     */
    Directory getDirectory();

    /**
     * CAP header component interface
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
        PackageInfo getPackage();

        /**
         * Get CAP package name info
         *
         * @return CAP package name info
         */
        PackageNameInfo getPackageName();

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
             * @return CAP package AID (hex string)
             */
            String getAID();
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

    /**
     * CAP directory component interface
     *
     * @author Edi Permadi
     */
    interface Directory {
        /**
         * Get CAP component sizes
         *
         * @return array of CAP component sizes
         */
        List<Integer> getComponentSizes();

        /**
         * Get static field information
         *
         * @return static field information
         */
        StaticFieldSizeInfo getStaticFieldSize();

        /**
         * Get count of import
         *
         * @return count of import
         */
        int getImportCount();

        /**
         * Get count of applet
         *
         * @return count of applet
         */
        int getAppletCount();

        /**
         * Get array of custom component info
         *
         * @return array of custom component info
         */
        List<CustomComponentInfo> getCustomComponents();

        /**
         * Static field size interface
         *
         * @author Edi Permadi
         */
        interface StaticFieldSizeInfo {
            int getImageSize();

            int getArrayInitCount();

            int getArrayInitSize();
        }

        /**
         * Custom component interface
         *
         * @author Edi Permadi
         */
        interface CustomComponentInfo {
            /**
             * Get component tag
             *
             * @return component tag
             */
            int getTag();

            /**
             * Get component AID
             *
             * @return component AID (hex string)
             */
            String getAID();
        }
    }
}
