package com.github.edipermadi.smartcard;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Javacard CAP directory component builder
 *
 * @author Edi Permadi
 */
final class CapDirectoryBuilder {
    private final List<Integer> componentSizes = new ArrayList<>();
    private Cap.Directory.StaticFieldSizeInfo staticFieldSize;
    private int importCount = -1;
    private int appletCount = -1;
    private final List<Cap.Directory.CustomComponentInfo> customComponents = new ArrayList<>();

    /**
     * Set static field size
     *
     * @param imageSize      image size
     * @param arrayInitCount array init count
     * @param arrayInitSize  array init size
     * @return this instance
     */
    public CapDirectoryBuilder setStaticFieldSize(final int imageSize, final int arrayInitCount, final int arrayInitSize) {
        this.staticFieldSize = new CapDirectoryStaticFieldSizeInfo(imageSize, arrayInitCount, arrayInitSize);
        return this;
    }

    /**
     * Set import count
     *
     * @param importCount new import count value
     * @return this instance
     */
    public CapDirectoryBuilder setImportCount(final int importCount) {
        if ((importCount < 0) || (importCount > 255)) {
            throw new IllegalArgumentException("invalid import count");
        }

        this.importCount = importCount;
        return this;
    }

    /**
     * Set applet count
     *
     * @param appletCount applet count
     * @return this instance
     */
    public CapDirectoryBuilder setAppletCount(final int appletCount) {
        if ((appletCount < 0) || (appletCount > 255)) {
            throw new IllegalArgumentException("invalid applet count");
        }
        this.appletCount = appletCount;
        return this;
    }

    /**
     * Add component size
     *
     * @param componentSize component size to be added
     * @return this instance
     */
    public CapDirectoryBuilder addComponentSize(final int componentSize) {
        if ((componentSize < 0) || (componentSize > 65535)) {
            throw new IllegalArgumentException("invalid component size");
        }
        componentSizes.add(componentSize);
        return this;
    }

    /**
     * Add custom component
     *
     * @param tag tag of custom component
     * @param aid AID of custom component
     * @return this instance
     */
    public CapDirectoryBuilder addCustomComponent(final int tag, final String aid) {
        if ((tag < 128) || (tag > 255)) {
            throw new IllegalArgumentException("tag of custom components is invalid");
        } else if (StringUtils.isEmpty(aid)) {
            throw new IllegalArgumentException("aid of custom components is empty");
        }
        customComponents.add(new CapDirectoryCustomComponentInfo(tag, aid));
        return this;
    }

    /**
     * Build CAP directory object
     *
     * @return CAP directory object
     */
    public Cap.Directory build() {
        if (componentSizes.isEmpty()) {
            throw new IllegalStateException("component sizes is mandatory");
        } else if (staticFieldSize == null) {
            throw new IllegalStateException("static-field-size is mandatory");
        } else if (importCount < 0) {
            throw new IllegalStateException("import count is mandatory");
        } else if (appletCount < 0) {
            throw new IllegalStateException("applet count is mandatory");
        }

        return new CapDirectory(this);
    }

    /**
     * Custom component info implementation
     *
     * @author Edi Permadi
     */
    static final class CapDirectoryCustomComponentInfo implements Cap.Directory.CustomComponentInfo {

        @SerializedName("tag")
        private final int tag;

        @SerializedName("aid")
        private final String aid;

        /**
         * Class constructor
         *
         * @param tag custom component tag
         * @param aid custom component aid
         */
        CapDirectoryCustomComponentInfo(final int tag, final String aid) {
            this.tag = tag;
            this.aid = aid;
        }

        @Override
        public int getTag() {
            return tag;
        }

        @Override
        public String getAID() {
            return aid;
        }
    }

    /**
     * Static Field Size Information Implementation
     *
     * @author Edi Permadi
     */
    static final class CapDirectoryStaticFieldSizeInfo implements Cap.Directory.StaticFieldSizeInfo {
        @SerializedName("image_size")
        private final int imageSize;

        @SerializedName("array_init_count")
        private final int arrayInitCount;

        @SerializedName("array_init_size")
        private final int arrayInitSize;

        /**
         * Class constructor
         *
         * @param imageSize      image size
         * @param arrayInitCount array init count
         * @param arrayInitSize  array init size
         */
        CapDirectoryStaticFieldSizeInfo(final int imageSize, final int arrayInitCount, final int arrayInitSize) {
            this.imageSize = imageSize;
            this.arrayInitCount = arrayInitCount;
            this.arrayInitSize = arrayInitSize;
        }

        @Override
        public int getImageSize() {
            return imageSize;
        }

        @Override
        public int getArrayInitCount() {
            return arrayInitCount;
        }

        @Override
        public int getArrayInitSize() {
            return arrayInitSize;
        }
    }

    /**
     * CAP directory implementation
     *
     * @author Edi Permadi
     */
    static final class CapDirectory implements Cap.Directory {
        @SerializedName("component_sizes")
        private final List<Integer> componentSizes;

        @SerializedName("static_field_size")
        private final StaticFieldSizeInfo staticFieldSize;

        @SerializedName("import_count")
        private final int importCount;

        @SerializedName("applet_count")
        private final int appletCount;

        @SerializedName("custom_components")
        private final List<CustomComponentInfo> customComponents;

        /**
         * Class constructor
         *
         * @param builder cap directory builder object
         */
        CapDirectory(CapDirectoryBuilder builder) {
            if (builder == null) {
                throw new IllegalArgumentException("builder is null");
            }
            this.componentSizes = builder.componentSizes;
            this.staticFieldSize = builder.staticFieldSize;
            this.importCount = builder.importCount;
            this.appletCount = builder.appletCount;
            this.customComponents = builder.customComponents;
        }

        @Override
        public List<Integer> getComponentSizes() {
            return componentSizes;
        }

        @Override
        public StaticFieldSizeInfo getStaticFieldSize() {
            return staticFieldSize;
        }

        @Override
        public int getImportCount() {
            return importCount;
        }

        @Override
        public int getAppletCount() {
            return appletCount;
        }

        @Override
        public List<CustomComponentInfo> getCustomComponents() {
            return customComponents;
        }

        @Override
        public String toString() {
            return null;
        }
    }
}
