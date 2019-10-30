package org.freedesktop.gstreamer.lowlevel.gl;

import com.sun.jna.Pointer;

public class GstWrappedGLContextPtr extends GstGLContextPtr {

    public GstWrappedGLContextPtr() {
    }

    public GstWrappedGLContextPtr(Pointer ptr) {
        super(ptr);
    }

}
