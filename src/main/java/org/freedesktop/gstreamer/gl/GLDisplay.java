package org.freedesktop.gstreamer.gl;

import org.freedesktop.gstreamer.Context;
import org.freedesktop.gstreamer.GstObject;
import org.freedesktop.gstreamer.glib.Natives;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLContextPtr;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLDisplayAPI;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLDisplayPtr;

public class GLDisplay extends GstObject {

    public static final String GTYPE_NAME = "GstGLDisplay";

    private final Handle handle;

    /**
     * Creates a new GLDisplay using the system property GST_GL_PLATFORM.
     */
    public GLDisplay() {
        this(new Handle(GstGLDisplayAPI.GSTGLDISPLAY_API.gst_gl_display_new(), true), true);
    }

    protected GLDisplay(Handle handle, boolean needRef) {
        super(handle, needRef);
        this.handle = handle;
    }

    protected GLDisplay(Initializer init) {
        this(new Handle(init.ptr.as(GstGLDisplayPtr.class, GstGLDisplayPtr::new), init.ownsHandle), init.needRef);
    }

    public void setToContext(Context context) {
        // The static GLDisplay type is used, not the real instance type.
        context.getWritableStructure().setObject("gst.gl.GLDisplay", GTYPE_NAME, this); // TODO this?
        // The native side also provides a dedicated setter doing the same thing:
        // gst_context_set_gl_display'.
    }

    public GLAPI getGLAPI() {
        return GLAPI.valueOf(GstGLDisplayAPI.GSTGLDISPLAY_API.gst_gl_display_get_gl_api(handle.getPointer()));
    }

    public boolean addContext(GLContext context) {
        GstGLContextPtr gstGLContextPtr = Natives.getPointer(context).as(GstGLContextPtr.class, GstGLContextPtr::new);
        return GstGLDisplayAPI.GSTGLDISPLAY_API.gst_gl_display_add_context(handle.getPointer(), gstGLContextPtr);
    }

    protected static class Handle extends GstObject.Handle {

        public Handle(GstGLDisplayPtr ptr, boolean ownsHandle) {
            super(ptr, ownsHandle);
        }

        @Override
        protected GstGLDisplayPtr getPointer() {
            return (GstGLDisplayPtr) super.getPointer();
        }

    }

}
