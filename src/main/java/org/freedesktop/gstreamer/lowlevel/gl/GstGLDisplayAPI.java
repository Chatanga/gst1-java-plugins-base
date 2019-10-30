package org.freedesktop.gstreamer.lowlevel.gl;

import org.freedesktop.gstreamer.lowlevel.GstContextPtr;
import org.freedesktop.gstreamer.lowlevel.GstNative;
import org.freedesktop.gstreamer.lowlevel.annotations.CallerOwnsReturn;

import com.sun.jna.Library;

/*
 * https://gstreamer.freedesktop.org/documentation/gl/gstgldisplay.html
 * 
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/gl/gstgldisplay.h
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/gl/gstgldisplay.c
 */
public interface GstGLDisplayAPI extends Library {

    GstGLDisplayAPI GSTGLDISPLAY_API = GstNative.load("gstgl", GstGLDisplayAPI.class);

    @CallerOwnsReturn GstGLDisplayPtr gst_gl_display_new();

    int gst_gl_display_get_gl_api(GstGLDisplayPtr display);

    void gst_context_set_gl_display(GstContextPtr context, GstGLDisplayPtr display);

    // boolean gst_gl_display_create_context(GstGLDisplayPtr display, GstGLContextPtr other_context, Pointer[] context, Pointer[] error);

    // GstGLContextPtr gst_gl_display_get_gl_context_for_thread(GstGLDisplayPtr display, GObject thread);

    boolean gst_gl_display_add_context(GstGLDisplayPtr display, GstGLContextPtr context);

}
