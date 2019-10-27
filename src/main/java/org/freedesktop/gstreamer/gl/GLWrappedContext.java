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

import static org.freedesktop.gstreamer.lowlevel.gl.GstGLContextAPI.GSTGLCONTEXT_API;

import java.util.EnumSet;

import org.freedesktop.gstreamer.glib.NativeFlags;
import org.freedesktop.gstreamer.glib.Natives;

public class GLWrappedContext extends GLContext {

	public static final String GTYPE_NAME = "GstGLWrappedContext";

	public GLWrappedContext(GLDisplay display, long handle, GLPlatform context_type, GLAPI apis) {
		super(Natives.initializer(
				GSTGLCONTEXT_API.ptr_gst_gl_context_new_wrapped(display, handle, context_type, apis.intValue())));
	}

	/**
	 * Wrap an existing OpenGL context.
	 * 
	 * @param display
	 * @param handle         the OpenGL context to wrap.
	 * @param context_type
	 * @param available_apis
	 */
	public GLWrappedContext(final GLDisplay display, long handle, GLPlatform context_type,
			EnumSet<GLAPI> available_apis) {
		super(Natives.initializer(GSTGLCONTEXT_API.ptr_gst_gl_context_new_wrapped(display, handle, context_type,
				NativeFlags.toInt(available_apis))));
	}

	/**
	 * This constructor is for internal use only.
	 * 
	 * @param init initialization data.
	 */
	protected GLWrappedContext(Initializer init) {
		super(init);
	}
}
