package org.freedesktop.gstreamer.gl;

import java.util.EnumSet;

import org.freedesktop.gstreamer.glib.NativeFlags;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLAPI;

public enum GLPlatform implements NativeFlags<GLPlatform> {

    /**
     * No platform.
     */
    GL_PLATFORM_NONE(0),
    /**
     * The EGL platform used primarily with the X11, wayland and android window
     * systems as well as on embedded Linux.
     */
    GL_PLATFORM_EGL(1 << 0),
    /**
     * The GLX platform used primarily with the X11 window system.
     */
    GL_PLATFORM_GLX(1 << 1),
    /**
     * The WGL platform used primarily on Windows.
     */
    GL_PLATFORM_WGL(1 << 2),
    /**
     * The CGL platform used primarily on OS X.
     */
    GL_PLATFORM_CGL(1 << 3),
    /**
     * The EAGL platform used primarily on iOS.
     */
    GL_PLATFORM_EAGL(1 << 4),
    /**
     * Any OpenGL platform.
     */
    GL_PLATFORM_ANY(0xffffffff);

    private final int value;

    private volatile String cachedName;

    private GLPlatform(int value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return this.value;
    }

    /**
     * Gets this GLPlatform's name.
     */
    @Override
    public String toString() {
        if (this.cachedName == null) {
            this.cachedName = GstGLAPI.GSTGLAPI.gst_gl_platform_to_string(intValue());
        }
        return this.cachedName;
    }

    public static final GLPlatform forName(String name) {
        EnumSet<GLPlatform> platforms = fromString(name);
        if (platforms.size() == 1) {
            return platforms.iterator().next();
        } else {
            return GL_PLATFORM_NONE;
        }
    }

    /**
     * @param platformFlags GLPlatform flags to stringify.
     *
     * @return A space separated string of the OpenGL platforms enabled in apiFlags.
     */
    public static String toString(EnumSet<GLPlatform> platformFlags) {
        return GstGLAPI.GSTGLAPI.gst_gl_platform_to_string(NativeFlags.toInt(platformFlags));
    }

    /**
     * @param names A space separated string of OpenGL platforms.
     *
     * @return The GLPlatform flags represented by names.
     */
    public static final EnumSet<GLPlatform> fromString(String names) {
        EnumSet<GLPlatform> platforms = NativeFlags.fromInt(GLPlatform.class,
                GstGLAPI.GSTGLAPI.gst_gl_platform_from_string(names));
        platforms.remove(GL_PLATFORM_ANY);
        return platforms;
    }

}
