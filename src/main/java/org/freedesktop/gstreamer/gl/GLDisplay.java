package org.freedesktop.gstreamer.gl;

import org.freedesktop.gstreamer.Context;
import org.freedesktop.gstreamer.GstObject;
import org.freedesktop.gstreamer.glib.Natives;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLDisplayAPI;

public class GLDisplay extends GstObject {

    public static final String GTYPE_NAME = "GstGLDisplay";

    /**
     * Creates a new GLDisplay using the system property GST_GL_PLATFORM.
     */
    public GLDisplay() {
        this(Natives.initializer(GstGLDisplayAPI.GSTGLDISPLAY_API.ptr_gst_gl_display_new()));
    }

    /**
     * This constructor is for internal use only.
     * 
     * @param init initialization data.
     */
    protected GLDisplay(Initializer init) {
        super(init);
    }

    public void setToContext(Context context) {
        // The static GLDisplay type is used, not the real instance type.
        context.getWritableStructure().setObject("gst.gl.GLDisplay", GTYPE_NAME, this);
        // The native side also provides a dedicated setter doing the same thing:
        // gst_context_set_gl_display'.
    }

    public GLAPI getGLAPI() {
        return GstGLDisplayAPI.GSTGLDISPLAY_API.gst_gl_display_get_gl_api(this);
    }

    public boolean addContext(GLContext context) {
        return GstGLDisplayAPI.GSTGLDISPLAY_API.gst_gl_display_add_context(this, context);
    }
}
