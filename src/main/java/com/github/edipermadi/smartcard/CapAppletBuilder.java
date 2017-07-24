package com.github.edipermadi.smartcard;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * CAP Applet component builder
 *
 * @author Edi Permadi
 */
class CapAppletBuilder {
    private final List<Cap.Applet.Info> applets = new ArrayList<>();

    /**
     * Add applet entry
     *
     * @param aid                 applet identifier
     * @param installMethodOffset applet installation method offset
     * @return this instance
     */
    CapAppletBuilder addApplet(final String aid, final int installMethodOffset) {
        applets.add(new CapAppletInfo(aid, installMethodOffset));
        return this;
    }

    /**
     * Build CAP applet component object
     *
     * @return CAP applet component object
     */
    public Cap.Applet build() {
        return new CapApplet(this);
    }

    /**
     * CAP Applet Component Object implementation
     *
     * @author Edi Permadi
     */
    static final class CapApplet implements Cap.Applet {
        @SerializedName("applets")
        private final List<Info> applets;

        /**
         * Class constructor
         *
         * @param builder CAP applet builder
         */
        CapApplet(final CapAppletBuilder builder) {
            this.applets = builder.applets;
        }

        @Override
        public List<Info> getApplets() {
            return applets;
        }
    }

    /**
     * CAP Applet information implementation
     *
     * @author Edi Permadi
     */
    static final class CapAppletInfo implements Cap.Applet.Info {
        @SerializedName("aid")
        private final String aid;

        @SerializedName("install_method_offset")
        private final int installMethodOffset;

        /**
         * Class constructor
         * @param aid applet identifier
         * @param installMethodOffset applet installation method offset
         */
        CapAppletInfo(final String aid, final int installMethodOffset) {
            this.aid = aid;
            this.installMethodOffset = installMethodOffset;
        }

        @Override
        public String getAID() {
            return aid;
        }

        @Override
        public int getInstallMethodOffset() {
            return installMethodOffset;
        }
    }
}
