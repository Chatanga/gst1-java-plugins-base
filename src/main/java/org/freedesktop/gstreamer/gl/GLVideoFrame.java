package org.freedesktop.gstreamer.gl;

import org.freedesktop.gstreamer.Buffer;
import org.freedesktop.gstreamer.Caps;
import org.freedesktop.gstreamer.lowlevel.GstBufferAPI;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoFrameAPI;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoFrameAPI.GstVideoFrameStruct;
import org.freedesktop.gstreamer.video.VideoInfo;

import com.sun.jna.Pointer;

public class GLVideoFrame {

	private final GstVideoFrameStruct struct;

	private int[] textures;

	public GLVideoFrame() {
		struct = new GstVideoFrameStruct();
	}

	public boolean map(Buffer buffer, Caps caps) {
		VideoInfo info = VideoInfo.createFromCaps(caps);
		map(info, buffer);
		return textures != null;
	}

	public int[] map(VideoInfo info, Buffer buffer) {
		int flags = GstBufferAPI.GST_MAP_READ | GLBaseMemory.GST_MAP_GL;
		if (GstVideoFrameAPI.GSTVIDEOFRAME_API.gst_video_frame_map(struct, info, buffer, flags)) {
			//textures = new int[struct.data.length];
			textures = new int[buffer.getMemoryCount()];
			for (int i = 0; i < textures.length; ++i) {
				Pointer p = struct.data[i];
				textures[i] = p != null ? p.getInt(0) : 0;
			}
			return textures;
		} else {
			textures = null;
			return null;
		}
	}

	public void unmap() {
		GstVideoFrameAPI.GSTVIDEOFRAME_API.gst_video_frame_unmap(struct);
		textures = null;
	}

	public int getTextureId(int index) {
		return textures[index];
	}

	public int[] getTextures() {
		return textures;
	}
}