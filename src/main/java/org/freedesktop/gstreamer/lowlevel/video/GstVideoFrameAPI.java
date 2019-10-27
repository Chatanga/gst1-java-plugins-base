package org.freedesktop.gstreamer.lowlevel.video;

import java.util.Arrays;
import java.util.List;

import org.freedesktop.gstreamer.Buffer;
import org.freedesktop.gstreamer.lowlevel.GstAPI;
import org.freedesktop.gstreamer.lowlevel.GstBufferAPI.MapInfoStruct;
import org.freedesktop.gstreamer.lowlevel.GstNative;
import org.freedesktop.gstreamer.lowlevel.video.GstVideoInfoAPI.GstVideoInfoStruct;
import org.freedesktop.gstreamer.video.VideoInfo;

import com.sun.jna.Pointer;

public interface GstVideoFrameAPI extends com.sun.jna.Library {

	GstVideoFrameAPI GSTVIDEOFRAME_API = GstNative.load("gstvideo", GstVideoFrameAPI.class);

	public static final int GST_VIDEO_MAX_PLANES = 4;

	public static final int GST_VIDEO_MAX_COMPONENTS = 4;

	public static final class GstVideoFrameStruct extends com.sun.jna.Structure {

		public volatile GstVideoInfoStruct info;
		public volatile int flags; // Always 0?

		public volatile Buffer /* (Buffer*) */ buffer;
		public volatile Pointer meta;
		public volatile int id;

		public volatile Pointer[] data = new Pointer[GST_VIDEO_MAX_PLANES];
		public volatile MapInfoStruct[] map = new MapInfoStruct[GST_VIDEO_MAX_PLANES];

		/* < private > */
		public volatile Pointer[] _gst_reserved = new Pointer[GstAPI.GST_PADDING];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("info", "flags", "buffer", "meta", "id", "data", "map", "_gst_reserved");
		}
	}

	boolean gst_video_frame_map(GstVideoFrameStruct frame, VideoInfo info, Buffer buffer, int flags);

	void gst_video_frame_unmap(GstVideoFrameStruct frame);
}
