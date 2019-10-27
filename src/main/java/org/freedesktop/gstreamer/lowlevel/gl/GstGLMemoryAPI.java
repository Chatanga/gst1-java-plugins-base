package org.freedesktop.gstreamer.lowlevel.gl;

import org.freedesktop.gstreamer.Memory;
import org.freedesktop.gstreamer.gl.GLMemory;
import org.freedesktop.gstreamer.lowlevel.GstNative;

public interface GstGLMemoryAPI extends com.sun.jna.Library {

	GstGLMemoryAPI GSTGLMEMORY_API = GstNative.load("gstgl", GstGLMemoryAPI.class);

	boolean gst_is_gl_memory(Memory mem);

	int gst_gl_memory_get_texture_id(GLMemory mem);

	int gst_gl_memory_get_texture_width(GLMemory mem);

	int gst_gl_memory_get_texture_height(GLMemory mem);
}
