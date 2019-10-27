package org.freedesktop.gstreamer.gl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.freedesktop.gstreamer.glib.NativeEnum;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLAPI;

public enum GLPlatform implements NativeEnum<GLPlatform> {

	GL_PLATFORM_NONE(0), //
	GL_PLATFORM_EGL(1 << 0), //
	GL_PLATFORM_GLX(1 << 1), //
	GL_PLATFORM_WGL(1 << 2), //
	GL_PLATFORM_CGL(1 << 3), //
	GL_PLATFORM_EAGL(1 << 4), //
	GL_PLATFORM_ANY(0xffffffff);

	private final int value;
	
	private String name;

	private static final Map<String, GLPlatform> typeMap = new ConcurrentHashMap<>();

	private GLPlatform(int val) {
		this.value = val;
	}

	@Override
	public int intValue() {
		return this.value;
	}

	public String getName() {
		if (this.name == null) {
			this.name = GstGLAPI.GSTGL_API.gst_gl_platform_to_string(this);
			typeMap.put(this.name, this);
		}
		return this.name;
	}

	/**
	 * Gets a GLPlatform that corresponds to the name.
	 * 
	 * @param name : "glx", "wgl", ...
	 * @return
	 */
	public static final GLPlatform forName(String name) {
		GLPlatform platform = typeMap.get(name);
		if (platform == null) {
			platform = GstGLAPI.GSTGL_API.gst_gl_platform_from_string(name);
			typeMap.put(name, platform);
		}

		return platform != null ? platform : GL_PLATFORM_NONE;
	}
}
