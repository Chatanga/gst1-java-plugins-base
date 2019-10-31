package org.freedesktop.gstreamer.gl;

import java.util.EnumSet;

import org.freedesktop.gstreamer.Context;
import org.freedesktop.gstreamer.GstObject;
import org.freedesktop.gstreamer.glib.NativeFlags;
import org.freedesktop.gstreamer.glib.Natives;
import org.freedesktop.gstreamer.lowlevel.GPointer;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLContextAPI;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLContextPtr;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLDisplayPtr;

/**
 * Opaque GL context.
 */
public class GLContext extends GstObject {

    public static final String GTYPE_NAME = "GstGLContext";

    /**
     * Create a new GLContext with the specified display.
     *
     * @param display A GLDisplay.
     */
    public GLContext(GLDisplay display) {
        this(new Handle(GstGLContextAPI.GSTGLCONTEXT_API.gst_gl_context_new( //
                Natives.getPointer(display).as(GstGLDisplayPtr.class, GstGLDisplayPtr::new)), true), true);
    }

    /**
     * Wrap an existing OpenGL context.
     * 
     * @param display
     * @param handle         the OpenGL context to wrap.
     * @param context_type
     * @param available_apis
     */
    public GLContext(GLDisplay display, long handle, GLPlatform context_type, EnumSet<GLAPI> available_apis) {
        this(new Handle(GstGLContextAPI.GSTGLCONTEXT_API.gst_gl_context_new_wrapped(
                Natives.getPointer(display).as(GstGLDisplayPtr.class, GstGLDisplayPtr::new), handle,
                context_type.intValue(), NativeFlags.toInt(available_apis)), true), true);
    }

    GLContext(Handle handle, boolean needRef) {
        super(handle, needRef);
        // No need to keep the handle around.
    }

    GLContext(Initializer init) {
        this(new Handle(init.ptr.as(GstGLContextPtr.class, GstGLContextPtr::new), init.ownsHandle), init.needRef);
    }

    public void setToContext(Context context) {
        // The static GLDisplay type is used, not the real instance type.
        context.getWritableStructure().setObject("context", GTYPE_NAME, this);
    }

    /**
     * 
     * @param contextType A GLPlatform specifying the type of context to retrieve.
     *
     * @return The OpenGL context handle current in the calling thread or -1 if none
     *         is set.
     */
    public static long getCurrentGLContext(GLPlatform contextType) {
        return GstGLContextAPI.GSTGLCONTEXT_API.gst_gl_context_get_current_gl_context(contextType.intValue());
    }

    protected static class Handle extends GstObject.Handle {

        public Handle(GstGLContextPtr ptr, boolean ownsHandle) {
            super(ptr, ownsHandle);
        }

        @Override
        protected GstGLContextPtr getPointer() {
            return (GstGLContextPtr) super.getPointer();
        }

        @Override
        protected void disposeNativeHandle(GPointer ptr) {
            GstGLContextAPI.GSTGLCONTEXT_API.gst_gl_context_destroy((GstGLContextPtr) ptr);
        }

    }
}
