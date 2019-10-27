package org.freedesktop.gstreamer.lowlevel.gl;

import org.freedesktop.gstreamer.gl.GLAPI;
import org.freedesktop.gstreamer.gl.GLContext;
import org.freedesktop.gstreamer.gl.GLDisplay;
import org.freedesktop.gstreamer.gl.GLPlatform;
import org.freedesktop.gstreamer.gl.GLWrappedContext;
import org.freedesktop.gstreamer.lowlevel.GType;
import org.freedesktop.gstreamer.lowlevel.GstNative;
import org.freedesktop.gstreamer.lowlevel.annotations.CallerOwnsReturn;

import com.sun.jna.Pointer;

/**
 * GstGLContext functions
 */
public interface GstGLContextAPI extends com.sun.jna.Library {

	GstGLContextAPI GSTGLCONTEXT_API = GstNative.load("gstgl", GstGLContextAPI.class);

	// GType gst_gl_wrapped_context_get_type();

	@CallerOwnsReturn Pointer ptr_gst_gl_context_new(GLDisplay display);

	GLContext gst_gl_context_new(GLDisplay display);

	void gst_gl_context_finalize(Pointer context);

	GType gst_gl_wrapped_context_get_type();

	@CallerOwnsReturn Pointer ptr_gst_gl_context_new_wrapped(GLDisplay display, long handle, GLPlatform context_type, int apis);

	@CallerOwnsReturn GLWrappedContext gst_gl_context_new_wrapped(GLDisplay display, long handle, GLPlatform context_type, int apis);

	boolean gst_gl_context_create(GLContext context, GLContext other_context, Pointer[] error);

	void gst_gl_context_destroy(Pointer /* GLContext */ context);

	// void gst_gl_context_finalize(Pointer context);

	GLContext gst_gl_context_get_current();

	GLDisplay gst_gl_context_get_display(GLContext context);

	GLPlatform gst_gl_context_get_gl_platform(GLContext context);

	GLAPI gst_gl_context_get_gl_api(GLContext context);

	// GObject gst_gl_context_get_thread(GLContext context);

	// long gst_gl_wrapped_context_get_gl_context(GLContext context);

	boolean gst_gl_context_activate(GLContext context, boolean activate);

	long gst_gl_context_get_current_gl_context(GLPlatform context_type);
}
