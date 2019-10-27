package org.freedesktop.gstreamer.lowlevel.gl;

import org.freedesktop.gstreamer.lowlevel.GstNative;

import com.sun.jna.Pointer;

public interface GstGLBufferAPI extends com.sun.jna.Library {

	GstGLBufferAPI GSTGLBUFFERAPI_API = GstNative.load("gstgl", GstGLBufferAPI.class);

	// Doesn't seem to work (even in the native examples provided).
	boolean gst_is_gl_buffer(Pointer /* GstMemory */ mem);
}
