package org.freedesktop.gstreamer.lowlevel.gl;

import org.freedesktop.gstreamer.Gst;
import org.freedesktop.gstreamer.glib.GObject;
import org.freedesktop.gstreamer.lowlevel.GType;
import org.freedesktop.gstreamer.lowlevel.GstNative;
import org.freedesktop.gstreamer.lowlevel.annotations.CallerOwnsReturn;

import com.sun.jna.Pointer;

/*
 * https://gstreamer.freedesktop.org/documentation/gl/gstglcontext.html
 * 
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/gl/gstglcontext.h
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/gl/gstglcontext.c
 */
public interface GstGLContextAPI extends com.sun.jna.Library {

    GstGLContextAPI GSTGLCONTEXT_API = GstNative.load("gstgl", GstGLContextAPI.class);

    @Gst.Since(minor = 4)
    @CallerOwnsReturn
    GstGLContextPtr gst_gl_context_new(GstGLDisplayPtr display);

    GType gst_gl_wrapped_context_get_type();

    @Gst.Since(minor = 4)
    @CallerOwnsReturn
    GstWrappedGLContextPtr gst_gl_context_new_wrapped(GstGLDisplayPtr display, long handle, int context_type, int apis);

    @Gst.Since(minor = 4)
    boolean gst_gl_context_create(GstGLContextPtr context, GstGLContextPtr other_context, Pointer[] error);

    @Gst.Since(minor = 6)
    void gst_gl_context_destroy(GstGLContextPtr context);

    @Gst.Since(minor = 6)
    GstGLContextPtr gst_gl_context_get_current();

    @Gst.Since(minor = 4)
    GstGLDisplayPtr gst_gl_context_get_display(GstGLContextPtr context);

    int gst_gl_context_get_gl_platform(GstGLContextPtr context);

    @Gst.Since(minor = 4)
    int gst_gl_context_get_gl_api(GstGLContextPtr context);

    @Gst.Since(minor = 6)
    GObject /* GThread */ gst_gl_context_get_thread(GstGLContextPtr context);

    @Gst.Since(minor = 4)
    boolean gst_gl_context_activate(GstGLContextPtr context, boolean activate);

    @Gst.Since(minor = 6)
    long gst_gl_context_get_current_gl_context(int context_type);

}
