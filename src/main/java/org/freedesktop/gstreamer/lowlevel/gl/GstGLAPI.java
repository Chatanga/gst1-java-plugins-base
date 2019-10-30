package org.freedesktop.gstreamer.lowlevel.gl;

import org.freedesktop.gstreamer.lowlevel.GstNative;

/*
 * https://gstreamer.freedesktop.org/documentation/gl/gstglapi.html
 * 
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/gl/gstglapi.h
 * https://gitlab.freedesktop.org/gstreamer/gst-plugins-base/blob/master/gst-libs/gst/gl/gstglapi.c
 */
public interface GstGLAPI extends com.sun.jna.Library {

    GstGLAPI GSTGLAPI = GstNative.load("gstgl", GstGLAPI.class);

    String gst_gl_api_to_string(int apis);

    int gst_gl_api_from_string(String apis);

    String gst_gl_platform_to_string(int platform);

    int gst_gl_platform_from_string(String platform);

}
