/*
 * Copyright (c) 2019 Christophe Lafolet
 * 
 * This file is part of gstreamer-java.
 *
 * This code is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU Lesser General Public License version 3 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License 
 * version 3 for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * version 3 along with this work.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.freedesktop.gstreamer.gl;

import java.util.EnumSet;

import org.freedesktop.gstreamer.glib.NativeFlags;
import org.freedesktop.gstreamer.glib.Natives;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLContextAPI;
import org.freedesktop.gstreamer.lowlevel.gl.GstGLDisplayPtr;
import org.freedesktop.gstreamer.lowlevel.gl.GstWrappedGLContextPtr;

public class GLWrappedContext extends GLContext {

    public static final String GTYPE_NAME = "GstGLWrappedContext";

    private final Handle handle;

    /**
     * Wrap an existing OpenGL context.
     * 
     * @param display
     * @param handle         the OpenGL context to wrap.
     * @param context_type
     * @param available_apis
     */
    public GLWrappedContext(GLDisplay display, long handle, GLPlatform context_type, EnumSet<GLAPI> available_apis) {
        this(new Handle(GstGLContextAPI.GSTGLCONTEXT_API.gst_gl_context_new_wrapped(
                Natives.getPointer(display).as(GstGLDisplayPtr.class, GstGLDisplayPtr::new), handle,
                context_type.intValue(), NativeFlags.toInt(available_apis)), true), true);
    }

    GLWrappedContext(Handle handle, boolean needRef) {
        super(handle, needRef);
        this.handle = handle;
    }

    GLWrappedContext(Initializer init) {
        this(new Handle(init.ptr.as(GstWrappedGLContextPtr.class, GstWrappedGLContextPtr::new), init.ownsHandle),
                init.needRef);
    }

    protected static class Handle extends GLContext.Handle {

        public Handle(GstWrappedGLContextPtr ptr, boolean ownsHandle) {
            super(ptr, ownsHandle);
        }

        @Override
        protected GstWrappedGLContextPtr getPointer() {
            return (GstWrappedGLContextPtr) super.getPointer();
        }

    }

}
