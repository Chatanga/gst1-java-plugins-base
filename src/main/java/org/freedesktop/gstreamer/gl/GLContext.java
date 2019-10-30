package org.freedesktop.gstreamer.gl;

import org.freedesktop.gstreamer.Context;
import org.freedesktop.gstreamer.GstObject;
import org.freedesktop.gstreamer.glib.Natives;
import org.freedesktop.gstreamer.lowlevel.GPointer;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLContextAPI;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLContextPtr;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLDisplayPtr;

// An OpenGL context, not a Gstreamer context.
public class GLContext extends GstObject {

    public static final String GTYPE_NAME = "GstGLContext";

    private final Handle handle;

    /**
     * Creates a new GLContext using the specified display.
     */
    public GLContext(GLDisplay display) {
        this(new Handle(GstGLContextAPI.GSTGLCONTEXT_API.gst_gl_context_new( //
                Natives.getPointer(display).as(GstGLDisplayPtr.class, GstGLDisplayPtr::new)), true), true);
    }

    GLContext(Handle handle, boolean needRef) {
        super(handle, needRef);
        this.handle = handle;
    }

    GLContext(Initializer init) {
        this(new Handle(init.ptr.as(GstGLContextPtr.class, GstGLContextPtr::new), init.ownsHandle), init.needRef);
    }

    public void setToContext(Context context) {
        // The static GLDisplay type is used, not the real instance type.
        context.getWritableStructure().setObject("context", GTYPE_NAME, this); // TODO this?
    }

    /**
     * Return the OpenGL context handle current to the calling thread
     * 
     * @param platform
     * @return the OpenGL context handle or NULL
     */
    public static long getCurrentGLContext(GLPlatform platform) {
        return GstGLContextAPI.GSTGLCONTEXT_API.gst_gl_context_get_current_gl_context(platform.intValue());
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
