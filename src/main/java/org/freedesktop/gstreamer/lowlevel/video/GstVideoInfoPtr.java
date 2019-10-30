package org.freedesktop.gstreamer.lowlevel.video;

import org.freedesktop.gstreamer.lowlevel.GstMiniObjectPtr;

import com.sun.jna.Pointer;

public class GstVideoInfoPtr extends GstMiniObjectPtr {

    public GstVideoInfoPtr() {
    }

    public GstVideoInfoPtr(Pointer ptr) {
        super(ptr);
    }

}
