package org.freedesktop.gstreamer.gl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.freedesktop.gstreamer.glib.NativeEnum;
import org.freedesktop.gstreamer.glib.NativeFlags;
import org.freedesktop.gstreamer.lowlevel.EnumMapper;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLAPI;

// Flags or enumeration?
public enum GLAPI implements NativeFlags<GLAPI> {

    GL_API_NONE(0), //
    GL_API_OPENGL(1 << 0), //
    GL_API_OPENGL3(1 << 1), //
    GL_API_GLES1(1 << 15), //
    GL_API_GLES2(1 << 16), //
    GL_API_ANY(0xffffffff);

    private static final Map<String, GLAPI> typeMap = new ConcurrentHashMap<>();

    private final int value;

    private String name;

    private GLAPI(int val) {
        this.value = val;
    }

    /** Gets the native value of this enum */
    @Override
    public int intValue() {
        return this.value;
    }

    /**
     * @param api flags to stringify.
     *
     * @return space separated string of the OpenGL api's enabled in api.
     */
    public String getName() {
        if (this.name == null) {
            this.name = GstGLAPI.GSTGLAPI.gst_gl_api_to_string(intValue());
            typeMap.put(this.name, this);
        }
        return this.name;
    }

    /**
     * Gets a GLAPI that corresponds to the native integer value.
     *
     * @param value the native value of the enum.
     * @return a GLAPI.
     */
    public static final GLAPI valueOf(int value) {
        return EnumMapper.getInstance().valueOf(value, GLAPI.class);
    }

    /**
     * Gets a GLAPI that corresponds to the name.
     * 
     * @param name : "opengl", "opengl3", ...
     * @return
     */
    public static final GLAPI forName(String name) {
        GLAPI api = typeMap.get(name);
        if (api == null) {
            api = NativeEnum.fromInt(GLAPI.class, GstGLAPI.GSTGLAPI.gst_gl_api_from_string(name));
            typeMap.put(name, api);
        }

        return api != null ? api : GL_API_NONE;
    }

}
