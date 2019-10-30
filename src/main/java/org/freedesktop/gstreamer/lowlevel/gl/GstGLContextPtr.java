package org.freedesktop.gstreamer.lowlevel.gl;

import org.freedesktop.gstreamer.lowlevel.GstObjectPtr;

import com.sun.jna.Pointer;

public class GstGLContextPtr extends GstObjectPtr {

    public GstGLContextPtr() {
    }

    public GstGLContextPtr(Pointer ptr) {
        super(ptr);
    }

}
