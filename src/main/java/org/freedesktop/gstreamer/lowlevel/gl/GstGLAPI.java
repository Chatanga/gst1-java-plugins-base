package org.freedesktop.gstreamer.lowlevel.gl;

import org.freedesktop.gstreamer.gl.GLAPI;
import org.freedesktop.gstreamer.gl.GLPlatform;
import org.freedesktop.gstreamer.lowlevel.GstNative;

public interface GstGLAPI extends com.sun.jna.Library {

	GstGLAPI GSTGL_API = GstNative.load("gstgl", GstGLAPI.class);

	String gst_gl_api_to_string(GLAPI api);

	GLAPI gst_gl_api_from_string(String api);

	String gst_gl_platform_to_string(GLPlatform platform);

	GLPlatform gst_gl_platform_from_string(String platform);
}
