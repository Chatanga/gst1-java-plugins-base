package org.freedesktop.gstreamer.gl;

import java.util.EnumSet;

import org.freedesktop.gstreamer.glib.NativeFlags;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLAPI;

public enum GLAPI implements NativeFlags<GLAPI> {

    /**
     * No API.
     */
    GL_API_NONE(0),
    /**
     * Desktop OpenGL up to and including 3.1. The compatibility profile when the
     * OpenGL version is >= 3.2.
     */
    GL_API_OPENGL(1 << 0),
    /**
     * Desktop OpenGL >= 3.2 core profile.
     */
    GL_API_OPENGL3(1 << 1),
    /**
     * OpenGL ES 1.x.
     */
    GL_API_GLES1(1 << 15),
    /**
     * OpenGL ES 2.x and 3.x.
     */
    GL_API_GLES2(1 << 16),
    /**
     * Any OpenGL API.
     */
    GL_API_ANY(0xffffffff);

    private final int value;

    private volatile String cachedName;

    private GLAPI(int value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return this.value;
    }

    /**
     * Gets this GLAPI's name.
     */
    @Override
    public String toString() {
        if (this.cachedName == null) {
            this.cachedName = GstGLAPI.GSTGLAPI.gst_gl_api_to_string(intValue());
        }
        return this.cachedName;
    }

    public static final GLAPI forName(String name) {
        EnumSet<GLAPI> apis = fromString(name);
        if (apis.size() == 1) {
            return apis.iterator().next();
        } else {
            return GL_API_NONE;
        }
    }

    /**
     * @param apiFlags GLAPI flags to stringify.
     *
     * @return A space separated string of the OpenGL api's enabled in apiFlags.
     */
    public static String toString(EnumSet<GLAPI> apiFlags) {
        return GstGLAPI.GSTGLAPI.gst_gl_api_to_string(NativeFlags.toInt(apiFlags));
    }

    /**
     * @param names A space separated string of OpenGL apis.
     *
     * @return The GLAPI flags represented by names.
     */
    public static final EnumSet<GLAPI> fromString(String names) {
        EnumSet<GLAPI> apis = NativeFlags.fromInt(GLAPI.class, GstGLAPI.GSTGLAPI.gst_gl_api_from_string(names));
        apis.remove(GL_API_ANY);
        return apis;
    }

}
