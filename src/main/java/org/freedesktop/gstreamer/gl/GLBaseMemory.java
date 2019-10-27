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

import org.freedesktop.gstreamer.Memory;

/**
 * No GTYPE_NAME defined for this class
 */
public class GLBaseMemory extends Memory {

	/**
	 * GST_MAP_GL:
	 *
	 * Flag indicating that we should map the GL object instead of to system memory.
	 *
	 * Combining #GST_MAP_GL with #GST_MAP_WRITE has the same semantics as though
	 * you are writing to OpenGL. Conversely, combining #GST_MAP_GL with
	 * #GST_MAP_READ has the same semantics as though you are reading from OpenGL.
	 */
	public static int GST_MAP_GL = (1 << 16) << 1; // GST_MAP_FLAG_LAST << 1

	/**
	 * This constructor is for internal use only.
	 * 
	 * @param init initialization data.
	 */
	protected GLBaseMemory(Initializer init) {
		super(init);
	}
}
