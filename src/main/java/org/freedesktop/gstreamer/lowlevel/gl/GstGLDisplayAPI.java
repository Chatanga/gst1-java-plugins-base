package org.freedesktop.gstreamer.lowlevel.gl;

import org.freedesktop.gstreamer.Context;
import org.freedesktop.gstreamer.gl.GLAPI;
import org.freedesktop.gstreamer.gl.GLContext;
import org.freedesktop.gstreamer.gl.GLDisplay;
import org.freedesktop.gstreamer.lowlevel.GstNative;
import org.freedesktop.gstreamer.lowlevel.annotations.CallerOwnsReturn;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface GstGLDisplayAPI extends Library {

	GstGLDisplayAPI GSTGLDISPLAY_API = GstNative.load("gstgl", GstGLDisplayAPI.class);

	@CallerOwnsReturn Pointer ptr_gst_gl_display_new();

	GLAPI gst_gl_display_get_gl_api(GLDisplay display);

	void gst_context_set_gl_display(Context context, GLDisplay display);

	// boolean gst_gl_display_create_context(GLDisplay display, GLContext
	// other_context, Pointer[] context, Pointer[] error);

	// GLContext gst_gl_display_get_gl_context_for_thread(GLDisplay display, GObject
	// thread);

	boolean gst_gl_display_add_context(GLDisplay display, GLContext context);
}
