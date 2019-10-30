package org.freedesktop.gstreamer.lowlevel.gl;

/*
 * https://gstreamer.freedesktop.org/documentation/gl/gstglmemory.html
 * 
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/gl/gstglmemory.h
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/gl/gstglmemory.c
 */
public interface GstGLMemoryAPI extends com.sun.jna.Library {
	
	/**
	 * GST_MAP_GL:
	 *
	 * Flag indicating that we should map the GL object instead of to system memory.
	 *
	 * Combining #GST_MAP_GL with #GST_MAP_WRITE has the same semantics as though
	 * you are writing to OpenGL. Conversely, combining #GST_MAP_GL with
	 * #GST_MAP_READ has the same semantics as though you are reading from OpenGL.
	 */
	public static int GST_MAP_GL = (1 << 16) << 1; // GST_MAP_FLAG_LAST << 1

}
