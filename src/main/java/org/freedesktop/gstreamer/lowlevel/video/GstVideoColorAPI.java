package org.freedesktop.gstreamer.lowlevel.video;

import java.util.Arrays;
import java.util.List;

/*
 * https://gstreamer.freedesktop.org/documentation/video/video-color.html
 * 
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/video/video-color.h
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/video/video-color.c
 */
public class GstVideoColorAPI {

    public static final class GstVideoColorimetryStruct extends com.sun.jna.Structure {

        public volatile int /* GstVideoColorRange */ range;

        public volatile int /* GstVideoColorMatrix */ matrix;

        public volatile int /* GstVideoTransferFunction */ transfer;

        public volatile int /* GstVideoColorPrimaries */ primaries;

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("range", "matrix", "transfer", "primaries");
        }

    }

}
