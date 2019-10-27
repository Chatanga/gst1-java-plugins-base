package org.freedesktop.gstreamer.gl;

import static org.freedesktop.gstreamer.lowlevel.gl.GstGLContextAPI.GSTGLCONTEXT_API;

import org.freedesktop.gstreamer.Context;
import org.freedesktop.gstreamer.GstObject;
import org.freedesktop.gstreamer.glib.Natives;
import org.freedesktop.gstreamer.lowlevel.GPointer;
import org.freedesktop.gstreamer.lowlevel.GstObjectPtr;

// An OpenGL context, not a Gstreamer context.
public class GLContext extends GstObject {

	public static final String GTYPE_NAME = "GstGLContext";

	/**
	 * Creates a new GLContext using the specified display.
	 */
	public GLContext(GLDisplay display) {
		this(Natives.initializer(GSTGLCONTEXT_API.ptr_gst_gl_context_new(display)));
	}

	/**
	 * This constructor is for internal use only.
	 * 
	 * @param init initialization data.
	 */
	protected GLContext(Initializer init) {
		super(new Handle(init.ptr.as(GstObjectPtr.class, GstObjectPtr::new), init.ownsHandle), init.needRef);
	}

	public void setToContext(Context context) {
		// The static GLDisplay type is used, not the real instance type.
		context.set("context", GTYPE_NAME, this);
	}

	/**
	 * Return the OpenGL context handle current to the calling thread
	 * 
	 * @param platform
	 * @return the OpenGL context handle or NULL
	 */
	public static long getCurrentGLContext(final GLPlatform platform) {
		return GSTGLCONTEXT_API.gst_gl_context_get_current_gl_context(platform);
	}

	private static final class Handle extends GstObject.Handle {

		public Handle(GstObjectPtr ptr, boolean ownsHandle) {
			super(ptr, ownsHandle);
		}

		@Override
		protected void disposeNativeHandle(GPointer ptr) {
			GSTGLCONTEXT_API.gst_gl_context_destroy(ptr.getPointer());
		}
	}
}
