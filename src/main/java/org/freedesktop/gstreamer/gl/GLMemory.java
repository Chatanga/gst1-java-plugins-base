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

import static org.freedesktop.gstreamer.lowlevel.gl.GstGLMemoryAPI.GSTGLMEMORY_API;

import org.freedesktop.gstreamer.Memory;

public class GLMemory extends GLBaseMemory {

	/**
	 * This constructor is for internal use only.
	 * 
	 * @param init initialization data.
	 */
	protected GLMemory(Initializer init) {
		super(init);
	}

	public static boolean isGLMemory(final Memory mem) {
		return GSTGLMEMORY_API.gst_is_gl_memory(mem);
	}

	public int getTextureId() {
		return GSTGLMEMORY_API.gst_gl_memory_get_texture_id(this);
	}

	public int getTextureWidth() {
		return GSTGLMEMORY_API.gst_gl_memory_get_texture_width(this);
	}

	public int getTextureHeight() {
		return GSTGLMEMORY_API.gst_gl_memory_get_texture_height(this);
	}

	@Override
	protected int getReadFlags() {
		return super.getReadFlags() | GST_MAP_GL;
	}

	@Override
	protected int getWriteFlags() {
		return super.getWriteFlags() | GST_MAP_GL;
	}
}
